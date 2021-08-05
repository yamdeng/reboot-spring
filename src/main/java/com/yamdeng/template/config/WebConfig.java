package com.yamdeng.template.config;

import com.yamdeng.template.common.DBMessageSource;
import com.yamdeng.template.common.MessageSourceService;
import com.yamdeng.template.exception.ApplicationException;
import com.yamdeng.template.web.ApiLogInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.messagesource.service}")
    private String messageSourceServiceClass;
    @Value("${app.messagesource.use-db}")
    private Boolean useDbMessageSource;
    @Value("${app.messagesource.refresh-ms}")
    private Long messageSourceRefreshMs;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/assets/", "/assets/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLogInterceptor())
            .addPathPatterns("/api/**");
    }

    @Bean
    public ApiLogInterceptor apiLogInterceptor() {
        return new ApiLogInterceptor();
    }

    @Bean
    public MessageSource messageSource() {
        if(useDbMessageSource) {
            DBMessageSource dbMessageSource = new DBMessageSource();
            dbMessageSource.setUseCodeAsDefaultMessage(true);
            return dbMessageSource;
        } else {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasenames("message.server", "message.client");
            messageSource.setDefaultEncoding("UTF-8");
            messageSource.setUseCodeAsDefaultMessage(true);
            messageSource.setCacheMillis(messageSourceRefreshMs);
            return messageSource;
        }
    }

    @Bean
    public MessageSourceService messageSourceService() {
        MessageSourceService messageSourceService = null;
        try {
            Class<?> clazz = Class.forName(messageSourceServiceClass);
            messageSourceService = 
                (MessageSourceService) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("messageSourceService Init Error", e);
            throw new ApplicationException(e);
             // Throwables.throwIfUnchecked(e);
        }
        return messageSourceService;
    }
    
}
