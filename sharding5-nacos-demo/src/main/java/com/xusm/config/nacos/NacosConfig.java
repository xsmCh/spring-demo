package com.xusm.config.nacos;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.xusm.component.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
public class NacosConfig{
    @Value("${dynamic.config-file.dataId}")
    private String dataId;

    @Value("${dynamic.config-file.group}")
    private String group;

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @Resource
    private DataSource dataSource;

    @Bean
    public ConfigService configService() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", nacosConfigProperties.getServerAddr());
        properties.put("namespace", nacosConfigProperties.getNamespace());
        properties.put("username", nacosConfigProperties.getUsername());
        properties.put("password", nacosConfigProperties.getPassword());
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.addListener(dataId, group, dynamicConfigFileListener());
        return configService;
    }

    private Listener dynamicConfigFileListener() {
        return new Listener() {
            @Override
            public Executor getExecutor() {
                return executor; // 返回null使用nacos默认的executor执行逻辑
            }

            @Override
            public void receiveConfigInfo(String content) {
                log.info("NACOS CONFIG CENTER, updated content:{}{}", System.lineSeparator(), content);
                // 在这里处理配置文件更新后的逻辑
                if (StringUtils.isEmpty(content)) {
                    log.info("updated content is empty");
                }
                log.info("创建最新数据源");
                DataSource newestDataSource = newShardingSphereDataSource(content);
                if (Objects.isNull(newestDataSource)) {
                    log.info("创建最新数据源失败");
                    return;
                }
                log.info("注册最新数据源");
                Map<Object, Object> targetDataSources = DynamicRoutingDataSource.getTargetDataSources();
                targetDataSources.put("newest", newestDataSource);
                DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
                ds.setTargetDataSources(targetDataSources);
                DynamicRoutingDataSource.setDataSource("newest");
                log.info("注册最新数据源完成");
            }
        };
    }

    private DataSource newShardingSphereDataSource(String yamlConfig) {
        try {
            return YamlShardingSphereDataSourceFactory.createDataSource(yamlConfig.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException | IOException e) {
            log.info("create new ShardingSphereDataSource fail", e);
        } catch (Exception e) {
            log.info("create new ShardingSphereDataSource fail, unexpect exception", e);
        }
        return null;
    }
}
