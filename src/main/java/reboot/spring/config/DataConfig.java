package reboot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reboot.spring.bean.data.ConnectionCheck;

@Configuration
@Import({DataSourceConfig.class})
public class DataConfig {

    @Bean
    public ConnectionCheck connectionCheck() {
        return new ConnectionCheck();
    }

}
