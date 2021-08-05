package com.yamdeng.template.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.yamdeng.template.common.LogMode;
import com.yamdeng.template.properties.BasicDataSourceProperties;
import com.yamdeng.template.properties.SecondDataSourceProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${app.logo}")
    String appLogo;
    @Value("${app.log.mode}")
    private LogMode logMode;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired(required = false)
    private BasicDataSourceProperties basicDataSourceProperties;

    @Autowired(required = false)
    private SecondDataSourceProperties secondDataSourceProperties;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/appInfo")
    public Map<String, Object> appInfo() {
        Map<String, Object> appInfoMap = new HashMap<>();
        appInfoMap.put("logo", appLogo);
        appInfoMap.put("logoMode", logMode);
        appInfoMap.put("basicDataSourceProperties", basicDataSourceProperties.toString());
        appInfoMap.put("secondDataSourceProperties", secondDataSourceProperties.toString());
        TreeMap<String, String> appPropertyMap = new TreeMap<>();
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        mutablePropertySources.stream()
            .filter(ps -> ps instanceof EnumerablePropertySource)
            .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
            .flatMap(Arrays::stream)
            .forEach(propertyName -> {
                appPropertyMap.put(propertyName, environment.getProperty(propertyName));
            });
        appInfoMap.put("appProperties", appPropertyMap);
        return appInfoMap;
    }

    @GetMapping("/beanNames")
    public List<String> beanNames() {
        List<String> beanNameList = Arrays.stream(applicationContext.getBeanDefinitionNames()).collect(Collectors.toList());
        return beanNameList;
    }

    @GetMapping("/loadedInfo")
    public Map<String, Object> loadedInfo() {
        Map<String, Object> loadedInfo = new HashMap<>();
        loadedInfo.put("activeProfiles", Arrays.toString(environment.getActiveProfiles()));
        return loadedInfo;
    }

    @PostMapping("/saveLog")
    public String saveLog(@RequestBody Map<String, String> logMap) {
        log.info("saveLog : " + logMap.get("message"));
        return "success";
    }

    @GetMapping("/health")
    public String health() {
        return "success";
    }

    @GetMapping("/messages")
    public Map<String, String> messages() {
        log.info("messageSource : " + messageSource);
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("server.welcome", messageSource.getMessage("server.welcome", null, null));
        messageMap.put("client.welcome", messageSource.getMessage("client.welcome", null, null));
        return messageMap;
    }

}
