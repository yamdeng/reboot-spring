package reboot.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import reboot.spring.boot.properties.CustomProperties;
import reboot.spring.boot.properties.YamdengProperties;

@Configuration
@EnableConfigurationProperties({YamdengProperties.class})
@PropertySource(value={"classpath:custom.properties"})
public class PropertiesConfig {

    @ConfigurationProperties(prefix = "custom")
    @Bean
    public CustomProperties customProperties() {
        return new CustomProperties();
    }

}
