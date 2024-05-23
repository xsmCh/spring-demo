package com.xusm.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    // 保存数据源的上下文，使用 ThreadLocal 确保线程安全
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();// 暂时不用线程的,用全局的
    private static String dataSourceKey; // 测试的时候先用全局的

    private static Map<Object, Object> targetDataSources;

    private static void setDataSourceKey(String dataSourceKey) {
        DynamicRoutingDataSource.dataSourceKey = dataSourceKey;
    }

    public static String getDataSourceKey() {
        return DynamicRoutingDataSource.dataSourceKey;
    }

    public static void setDataSource(String dataSourceKey) {
        log.info("设置当前数据源为: {}", dataSourceKey);
        if (StringUtils.isEmpty(dataSourceKey)) {
            throw new RuntimeException("数据源不能为空");
        }
        if (!targetDataSources.containsKey(dataSourceKey)) {
            throw new RuntimeException("数据源不存在");
        }
        setDataSourceKey(dataSourceKey);
    }

    public static String currentDatasource() {
        String value = getDataSourceKey();
        log.info("当前数据源为: {}", value);
        return value;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceKey();
    }

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        DynamicRoutingDataSource.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
        afterPropertiesSet();
    }

    public static Map<Object, Object> getTargetDataSources() {
        return targetDataSources;
    }
}
