package reboot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reboot.spring.bean.profile.DevProfileService;
import reboot.spring.bean.profile.IProfileService;

@Profile("dev")
@Configuration
public class DevProfileConfig {

    @Bean
    public IProfileService profileService() {
        return new DevProfileService();
    }

}
