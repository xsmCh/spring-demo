package com.xusm.config.dynamic;

import com.xusm.component.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.driver.ShardingSphereURLManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DynamicDatasourceConfig {

    @Bean
    public DynamicRoutingDataSource dynamicDatasource() throws SQLException, IOException {
        // 创建数据源
        DataSource dataSource1 = database1();
        DataSource dataSource2 = database2();
        // 创建目标数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("db1", dataSource1);
        targetDataSources.put("db2", dataSource2);
        // 初始化DynamicRoutingDataSource
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        // 设置目标数据源
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource1);
        // 设置当前数据源
        DynamicRoutingDataSource.setDataSource("db1");
        // 返回数据源
        return dynamicRoutingDataSource;
    }

    public DataSource database1() throws SQLException, IOException {
        String url = "jdbc:shardingsphere:classpath:sharding-jdbc-single.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        log.info("start init sharding jdbc");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(ShardingSphereURLManager.getContent(url, urlPrefix));
        log.info("end init sharding jdbc");
        return dataSource;
    }

    public DataSource database2() throws SQLException, IOException {
        String url = "jdbc:shardingsphere:classpath:sharding-jdbc-m1.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        log.info("start init sharding jdbc");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(ShardingSphereURLManager.getContent(url, urlPrefix));
        log.info("end init sharding jdbc");
        return dataSource;
    }

    public DataSource database3() throws SQLException, IOException {
        String url = "jdbc:shardingsphere:classpath:sharding-jdbc.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        log.info("start init sharding jdbc");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(ShardingSphereURLManager.getContent(url, urlPrefix));
        log.info("end init sharding jdbc");
        return dataSource;
    }
}
