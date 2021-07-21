package com.yamdeng.template.controller;

import com.yamdeng.common.service.CountService;
import com.yamdeng.common.service.ISpringMetaService;
import com.yamdeng.common.service.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Qualifier("springMetaService2")
    @Autowired
    ISpringMetaService springMetaService2;

    @Autowired
    ISpringMetaService springMetaService;

    @Autowired
    LicenseService licenseService;

    @Autowired
    CountService countService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello23";
    }

}
