package com.yamdeng.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yamdeng.template.properties.BasicDataSourceProperties;
import com.yamdeng.template.properties.SecondDataSourceProperties;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${app.logo}")
    String appLogo;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private BasicDataSourceProperties basicDataSourceProperties;

    @Autowired(required = false)
    private SecondDataSourceProperties secondDataSourceProperties;

    @GetMapping("/appInfo")
    public Map<String, Object> appInfo() {
        Map<String, Object> appInfoMap = new HashMap<>();
        appInfoMap.put("logo", appLogo);
        appInfoMap.put("basicDataSourceProperties", basicDataSourceProperties.toString());
        appInfoMap.put("secondDataSourceProperties", secondDataSourceProperties.toString());
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

}
