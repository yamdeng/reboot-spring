package com.yamdeng.template.config;

import com.yamdeng.template.common.DBMessageSource;
import com.yamdeng.template.common.MessageSourceService;
import com.yamdeng.template.common.ViewTemplateType;
import com.yamdeng.template.exception.ApplicationException;
import com.yamdeng.template.web.ApiLogInterceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.messagesource.service}")
    private String messageSourceServiceClass;
    @Value("${app.messagesource.use-db}")
    private Boolean useDbMessageSource;
    @Value("${app.messagesource.refresh-ms}")
    private Long messageSourceRefreshMs;
    @Value("${app.view.jsp.enable}")
    private Boolean jspEnable;
    @Value("${app.view.jsp.prefix}")
    private String jspPrefix;
    @Value("${app.view.template-type}")
    private ViewTemplateType viewTemplateType;
    @Value("${app.view.resource-locations}")
    private String resourceLocations;
    @Value("${app.messagesource.default-locale}")
    private String defaultLocale;
    @Value("${app.locale.cookie-name}")
    private String localeCookieName;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/assets/**")
        //     .addResourceLocations("classpath:/assets/", "/assets/");
        String[] resourceLocationsArray = StringUtils.split(resourceLocations, ",");
        if(resourceLocationsArray != null && resourceLocationsArray.length > 0) {
            for(int arrIndex=0; arrIndex<resourceLocationsArray.length; arrIndex++) {
                String resourceLocation = resourceLocationsArray[arrIndex];
                registry
                        .addResourceHandler(resourceLocation + "/**")
                        .addResourceLocations(resourceLocation + "/", "classpath:" + resourceLocation + "/");
            }
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLogInterceptor())
            .addPathPatterns("/api/**");
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if(jspEnable) {
            registry.jsp(jspPrefix, ".jsp");
        }
        // registry.viewResolver(thymeleafViewResolver());
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
            messageSource.setDefaultEncoding("UTF-8");
            // spring 기본 properties 설정을 적용할 경우
            // messageSource.setBasename("messages");
            messageSource.setBasenames("message.server", "message.client", "message.validation");
            messageSource.setUseCodeAsDefaultMessage(true);
            messageSource.setDefaultLocale(StringUtils.parseLocale(defaultLocale));
            messageSource.setCacheMillis(messageSourceRefreshMs);
            return messageSource;
        }
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
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
            // 정적 유틸 클래스로 예외 던지기
            // Throwables.throwIfUnchecked(e);
        }
        return messageSourceService;
    }

    @Value("${app.locale.change-parameter:lang}")
    private String localeChangeParameter;

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(localeChangeParameter);
        return interceptor;
    }

    @ConditionalOnProperty(
        value="app.locale.use-cookie", 
        havingValue = "true",
        matchIfMissing = false)
    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(StringUtils.parseLocaleString(defaultLocale));
        resolver.setCookieName(localeCookieName);
        resolver.setCookieMaxAge(4800);
        return resolver;
    }
    
}
