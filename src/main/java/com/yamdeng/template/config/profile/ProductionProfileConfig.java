package com.yamdeng.template.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("production")
@Configuration
public class ProductionProfileConfig {
    
}
