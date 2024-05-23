package com.xusm.web;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.jdbc.core.driver.ShardingSphereURLManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/dynamic-config")
public class DynamicConfigController {
    @Value("${dynamic.config-file.dataId}")
    private String dataId;

    @Value("${dynamic.config-file.group}")
    private String group;

    @Resource
    private ConfigService configService;

    @GetMapping("/fetch")
    public String fetchNacosConfig() throws NacosException {
        String content = configService.getConfig(dataId, group, 3000L);
        log.info("NACOS CONFIG CENTER, dataId={}, group={}, content:{}{}", dataId, group, System.lineSeparator(), content);
        return content;
    }

    @PostMapping("/publish")
    public String publishNacosConfig() throws NacosException {
        String url = "jdbc:shardingsphere:classpath:sharding-jdbc.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        byte[] yamlBytes = ShardingSphereURLManager.getContent(url, urlPrefix);
        String content = new String(yamlBytes, StandardCharsets.UTF_8);
        boolean success = configService.publishConfig(dataId, group, content);
        log.info("NACOS CONFIG CENTER, dataId={}, group={}, publish={}", dataId, group, success);
        return fetchNacosConfig();
    }
}
