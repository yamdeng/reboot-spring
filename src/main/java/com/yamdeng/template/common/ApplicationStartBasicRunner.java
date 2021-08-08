package com.yamdeng.template.common;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class ApplicationStartBasicRunner implements CommandLineRunner {

    @Value("${app.logo}")
    String appLogo;

    @Autowired
    private Validator validator;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    public void run(String... args) {
    	log.info("application started [" + appLogo + "] :" + Arrays.toString(args));
        log.info("validator :" + validator);
        log.info("conversionService :" + conversionService.hashCode());
        log.info("messageSource :" + messageSource);
        log.info("localeResolver :" + localeResolver);
    }

    
}
