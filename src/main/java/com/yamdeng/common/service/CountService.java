package com.yamdeng.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class CountService implements InitializingBean, DisposableBean {

    final static Logger logger = LoggerFactory.getLogger(LicenseService.class);

    public CountService() {

        logger.info(
            "============================== CountService constructor call ==============================");

    }

    @Override
    public void destroy() throws Exception {
        logger.info("@@@@@@@@@@@@@@@ destroy @@@@@@@@@@@@@@@");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("@@@@@@@@@@@@@@@ afterPropertiesSet @@@@@@@@@@@@@@@");

    }
}
