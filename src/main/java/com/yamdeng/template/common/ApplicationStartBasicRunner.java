package com.yamdeng.template.common;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class ApplicationStartBasicRunner implements CommandLineRunner {

    @Value("${app.logo}")
    String appLogo;

    public void run(String... args) {
    	log.info("application started [" + appLogo + "] :" + Arrays.toString(args));
    }

    
}
