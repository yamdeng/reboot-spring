package reboot.spring.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("stage")
@Configuration
public class StageProfileConfig {

    @Bean
    public Map<String, String> stringMap() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", "yamdeng");
        return stringMap;
    }

}
