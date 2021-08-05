package com.yamdeng.template.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

@Slf4j
@Configuration
public class CommonConfig {

    @Value("${app.logo}")
    String appLogo;

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;

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
    }

    @PreDestroy
    public void destroy() {
        log.info("application close");
    }

}
