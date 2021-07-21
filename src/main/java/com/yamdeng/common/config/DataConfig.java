package com.yamdeng.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.111.211:3306/chat?characterEncoding=utf8&useSSL=false");
        ds.setUsername("yamdeng");
        ds.setPassword("dB1234**");
        return ds;
    }


}
