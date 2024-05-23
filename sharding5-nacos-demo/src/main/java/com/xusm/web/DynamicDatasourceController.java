package com.xusm.web;

import com.xusm.component.DynamicRoutingDataSource;
import com.xusm.config.dynamic.DynamicDatasourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/dynamic")
public class DynamicDatasourceController {
    @Resource
    private DataSource dataSource;

    @Resource
    private DynamicDatasourceConfig config;

    @GetMapping("/all")
    public Set<Object> allDatasource() {

        return DynamicRoutingDataSource.getTargetDataSources().keySet();
    }

    @GetMapping("/current")
    public String currentDatasource() {
        return DynamicRoutingDataSource.currentDatasource();
    }

    @PostMapping("/change")
    public String changeCurrentDatasource(String dataSourceKey) {
        DynamicRoutingDataSource.setDataSource(dataSourceKey);
        return DynamicRoutingDataSource.getDataSourceKey();
    }

    @PostMapping("/add")
    public Set<Object> addDatasource() throws SQLException, IOException {
        DataSource dataSource3 = config.database3();
        Map<Object, Object> targetDataSources = DynamicRoutingDataSource.getTargetDataSources();
        targetDataSources.put("db3", dataSource3);

        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.setTargetDataSources(targetDataSources);
        ds.afterPropertiesSet();

        return DynamicRoutingDataSource.getTargetDataSources().keySet();
    }

    @PostMapping("/delete")
    public Set<Object> deleteDataSource() {
        Map<Object, Object> targetDataSources = DynamicRoutingDataSource.getTargetDataSources();
        targetDataSources.remove("db3");

        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.setTargetDataSources(targetDataSources);
        ds.afterPropertiesSet();

        return DynamicRoutingDataSource.getTargetDataSources().keySet();
    }
}
