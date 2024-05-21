package com.xusm.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.xusm.mapper")
@Configuration
public class MybatisConfig {
}
