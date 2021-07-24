package reboot.spring.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstSpringConfig {

    @Data
    public static class FirstBean {
        private String name = "yamdeng";
        private int age = 38;
    }

    @Bean
    public FirstBean firstBean() {
        FirstBean firstBean = new FirstBean();
        return firstBean;
    }

}