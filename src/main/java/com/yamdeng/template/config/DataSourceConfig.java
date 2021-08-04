package com.yamdeng.template.config;

import javax.sql.DataSource;

import com.yamdeng.template.properties.BasicDataSourceProperties;
import com.yamdeng.template.properties.SecondDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Autowired(required = true)
    private BasicDataSourceProperties basicDataSourceProperties;

    @Autowired(required = false)
    private SecondDataSourceProperties secondDataSourceProperties;

    @Bean("dataSource")
    public DataSource dataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(basicDataSourceProperties.getUrl());
        hikariConfig.setUsername(basicDataSourceProperties.getUsername());
        hikariConfig.setPassword(basicDataSourceProperties.getPassword());
        hikariConfig.setDriverClassName(basicDataSourceProperties.getDriverClassName());
        hikariConfig.setPoolName("basicAppPool");
        hikariConfig.setLeakDetectionThreshold(100);
        return new HikariDataSource(hikariConfig);
    }

    @ConditionalOnProperty(
        value="app.datasource.use-multiple", 
        havingValue = "true",
        matchIfMissing = false)
    @Bean("secondDataSource")
    public DataSource secondDataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(secondDataSourceProperties.getUrl());
        hikariConfig.setUsername(secondDataSourceProperties.getUsername());
        hikariConfig.setPassword(secondDataSourceProperties.getPassword());
        hikariConfig.setDriverClassName(secondDataSourceProperties.getDriverClassName());
        hikariConfig.setPoolName("subAppPool");
        hikariConfig.setLeakDetectionThreshold(100);
        return new HikariDataSource(hikariConfig);
    }

}
