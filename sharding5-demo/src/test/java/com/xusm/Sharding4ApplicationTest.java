package com.xusm;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Sharding4ApplicationTest {
    @Test
    public void test_datasource() throws SQLException {
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(null);
        ShardingSphereDataSource shardingSphereDataSource = (ShardingSphereDataSource) dataSource;
    }
}
