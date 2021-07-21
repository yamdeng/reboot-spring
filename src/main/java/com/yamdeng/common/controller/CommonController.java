package com.yamdeng.common.controller;

import com.yamdeng.common.dto.LicenseInfoDto;
import com.yamdeng.common.service.CountService;
import com.yamdeng.common.service.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    final static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    LicenseService licenseService;

    @Autowired
    CountService countService;

    @ResponseBody
    @GetMapping("/health")
    public String health() {
        logger.trace("trace -- health");
        logger.trace("debug -- health");
        logger.trace("info -- health");
        logger.trace("warn -- health");
        logger.trace("error -- health");
        return "bounce bounce";
    }

    @ResponseBody
    @GetMapping("/version")
    public LicenseInfoDto getLicenseInfo() {
        return licenseService.getLicenseInfo();
    }

}
