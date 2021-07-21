package com.yamdeng.common.config;

import com.yamdeng.common.controller.CommonController;
import com.yamdeng.common.service.CountService;
import com.yamdeng.common.service.ISpringMetaService;
import com.yamdeng.common.service.LicenseService;
import com.yamdeng.common.service.SpringMetaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommonCustomConfig {

    @Bean
    public LicenseService licenseService() {
        return new LicenseService();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ISpringMetaService springMetaService() {
        return new SpringMetaService();
    }

    @Qualifier("springMetaService2")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ISpringMetaService springMetaService2() {
        return new SpringMetaService();
    }

    @Bean
    public CommonController commonController() {
        return new CommonController();
    }

    @Bean
    @Scope("prototype")
    public CountService countService() {
        return new CountService();
    }

}
