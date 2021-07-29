package reboot.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import reboot.spring.boot.properties.CustomProperties;

@Configuration
@ConfigurationPropertiesScan({ "reboot.spring.boot.properties" })
@PropertySource(value={"classpath:custom.properties", "classpath:platform.properties"})
public class PropertiesConfig {

    @ConfigurationProperties(prefix = "custom")
    @Bean
    public CustomProperties customProperties() {
        return new CustomProperties();
    }

}
