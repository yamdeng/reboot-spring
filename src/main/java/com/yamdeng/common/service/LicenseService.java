package com.yamdeng.common.service;

import com.yamdeng.common.dto.LicenseInfoDto;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class LicenseService {

    final static Logger logger = LoggerFactory.getLogger(LicenseService.class);

    @Autowired
    ISpringMetaService springMetaService;

    private String name = "yamdeng-template";
    private String version = "0.1";

    public LicenseInfoDto getLicenseInfo() {
        return new LicenseInfoDto(name, version, springMetaService.getSpringVersion(),
            springMetaService.getSpringBootVersion());
    }

    @PostConstruct
    public void init() {
        logger.info("license service init");
    }

    @PreDestroy
    public void destroy() {
        logger.info("license service destroy");
    }

}
