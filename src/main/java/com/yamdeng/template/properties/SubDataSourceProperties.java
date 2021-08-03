package com.yamdeng.template.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "app.datasource")
public class SubDataSourceProperties {

    private String url;
    private String driverClassName;
    private String username;
    private String password;
    
}
