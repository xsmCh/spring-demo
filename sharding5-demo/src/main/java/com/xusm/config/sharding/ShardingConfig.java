package com.xusm.config.sharding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ShardingConfig implements ApplicationRunner {
    @Value("${sharding.file}")
    private String shardingFile;

    @Override
    public void run(ApplicationArguments args) {
        log.info("sharding file: {}", shardingFile);
    }
}
