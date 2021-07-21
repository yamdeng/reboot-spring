package com.yamdeng.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonCustomConfig.class, AopConfig.class, DataConfig.class})
public class RootConfig {

}
