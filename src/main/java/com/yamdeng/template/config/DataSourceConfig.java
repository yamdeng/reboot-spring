package com.yamdeng.template.config;

import javax.sql.DataSource;

import com.yamdeng.template.properties.BasicDataSourceProperties;
import com.yamdeng.template.properties.SubDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Autowired(required = true)
    private BasicDataSourceProperties basicDataSourceProperties;

    @Autowired(required = false)
    private SubDataSourceProperties subDataSourceProperties;

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

    @Bean("subDataSource")
    public DataSource subDataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(subDataSourceProperties.getUrl());
        hikariConfig.setUsername(subDataSourceProperties.getUsername());
        hikariConfig.setPassword(subDataSourceProperties.getPassword());
        hikariConfig.setDriverClassName(subDataSourceProperties.getDriverClassName());
        hikariConfig.setPoolName("subAppPool");
        hikariConfig.setLeakDetectionThreshold(100);
        return new HikariDataSource(hikariConfig);
    }

}
