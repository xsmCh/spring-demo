package com.xusm.config.dynamic;

import com.xusm.component.DynamicRoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
        HikariDataSource m0 = hikariDataSource("1-m0", "jdbc:mysql://localhost:3306/store_db?useUnicode=true", "root", "root");
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("m0", m0);

        String url = "jdbc:shardingsphere:classpath:sharding-jdbc-single.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        log.info("start init sharding jdbc");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(dataSourceMap, ShardingSphereURLManager.getContent(url, urlPrefix));
        log.info("end init sharding jdbc");
        return dataSource;
    }

    public DataSource database2() throws SQLException, IOException {
        HikariDataSource m0 = hikariDataSource("2-m0", "jdbc:mysql://localhost:3306/store_db?useUnicode=true", "root", "root");
        HikariDataSource m1 = hikariDataSource("2-m1", "jdbc:mysql://localhost:3306/product_db_1?useUnicode=true", "root", "root");
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("m0", m0);
        dataSourceMap.put("m1", m1);

        String url = "jdbc:shardingsphere:classpath:sharding-jdbc-m1.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        log.info("start init sharding jdbc");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(dataSourceMap, ShardingSphereURLManager.getContent(url, urlPrefix));
        log.info("end init sharding jdbc");
        return dataSource;
    }

    public DataSource database3() throws SQLException, IOException {
        HikariDataSource m0 = hikariDataSource("3-m0", "jdbc:mysql://localhost:3306/store_db?useUnicode=true", "root", "root");
        HikariDataSource m1 = hikariDataSource("3-m1", "jdbc:mysql://localhost:3306/product_db_1?useUnicode=true", "root", "root");
        HikariDataSource m3 = hikariDataSource("3-m2", "jdbc:mysql://localhost:3306/product_db_2?useUnicode=true", "root", "root");
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("m0", m0);
        dataSourceMap.put("m1", m1);
        dataSourceMap.put("m3", m3);

        String url = "jdbc:shardingsphere:classpath:sharding-jdbc.yaml";
        String urlPrefix = "jdbc:shardingsphere:";
        log.info("start init sharding jdbc");
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(dataSourceMap, ShardingSphereURLManager.getContent(url, urlPrefix));
        log.info("end init sharding jdbc");
        return dataSource;
    }

    private HikariDataSource hikariDataSource(String poolName, String url, String userName, String passwd) {
        // 创建Hikari配置
        HikariConfig config = new HikariConfig();

        // 设置JDBC URL、用户名和密码
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(passwd);

        // 配置Hikari连接池参数
        config.setMaximumPoolSize(10);        // 设置最大连接数
        config.setMinimumIdle(5);             // 设置最小空闲连接数
        config.setIdleTimeout(30000);         // 设置连接空闲超时时间，单位毫秒
        config.setConnectionTimeout(30000);   // 设置连接超时时间，单位毫秒
        config.setMaxLifetime(1800000);       // 设置连接的最大生命周期，单位毫秒
        config.setPoolName(poolName);     // 设置连接池的名字

        // 创建HikariDataSource实例
        return new HikariDataSource(config);
    }
}
