package com.yamdeng.template.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.yamdeng.template.common.GlobalApplicationEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CommonConfig {

    @Value("${app.logo}")
    String appLogo;

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationAvailability applicationAvailability;

    @PostConstruct
    public void init() {
        String[] profiles = environment.getActiveProfiles();
        log.info("app logo : " + appLogo);
        log.info("active profiles : " + Arrays.toString(profiles));
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("========== beanName display start ==========");
        Arrays.sort(beanNames);
        for(int index=0; index<beanNames.length; index++) {
            log.info(beanNames[index]);
        }
        log.info("@@@@@@@@@@ beanName totacl count : " + beanNames.length + "@@@@@@@@@@");
        log.info("========== beanName display end ==========");
        log.info("applicationAvailability : " + applicationAvailability);
    }

    @PreDestroy
    public void destroy() {
        log.info("application close");
    }

    @ConditionalOnProperty(
        value="app.event.use-global-listener", 
        havingValue = "true",
        matchIfMissing = false)
    @Bean
    public GlobalApplicationEventListener globalApplicationEventListener() {
        return new GlobalApplicationEventListener();
    }

}
