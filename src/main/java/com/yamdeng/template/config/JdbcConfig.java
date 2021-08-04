package com.yamdeng.template.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfig {

    @Autowired
    private DataSource dataSource;

    @Qualifier("subDataSource")
    @Autowired(required = false)
    private DataSource subDataSource;
    
}
