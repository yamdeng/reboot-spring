package com.yamdeng.template.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(2)
public class ApplicationStarDataBaseRunner implements CommandLineRunner {

    @Value("${app.logo}")
    private String appLogo;

    @Autowired
    private Environment environment;

    public void run(String... args) {
    	log.info("========== database init start ==========");

        log.info("========== database init end ==========");
    }

    
}
