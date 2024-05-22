package com.xusm.config.sharding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Configuration
public class ShardingConfig implements ApplicationRunner {
    @Value("${sharding.file}")
    private String shardingFile;

    @Resource
    private DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws SQLException {
        // 输出启动的sharding file
        log.info("sharding file: {}", shardingFile);
        // 强制初始化数据源
        try (Connection connection = dataSource.getConnection()) {
            // This will ensure that the HikariDataSource is started at application startup
            log.info("HikariDataSource has been initialized and a connection has been acquired.");
        }
    }
}
