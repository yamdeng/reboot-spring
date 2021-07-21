package com.yamdeng.common.config;

import com.yamdeng.common.aop.ControllerTimeCheckAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public ControllerTimeCheckAdvice controllerTimeCheckAdvice() {
        return new ControllerTimeCheckAdvice();
    }

}
