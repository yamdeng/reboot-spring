package com.yamdeng.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

public class SpringMetaService implements ISpringMetaService {

    final static Logger logger = LoggerFactory.getLogger(SpringMetaService.class);


    @Override
    public String getSpringVersion() {
        return SpringVersion.getVersion();
    }

    @Override
    public String getSpringBootVersion() {
        return SpringBootVersion.getVersion();
    }

    public void init() {
        logger.info("springMetaService init");
    }

    public void destroy() {
        logger.info("springMetaService destroy");
    }

}
