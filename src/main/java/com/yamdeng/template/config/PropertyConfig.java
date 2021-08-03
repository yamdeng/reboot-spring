package com.yamdeng.template.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("${app.root-package}")
public class PropertyConfig {
}
