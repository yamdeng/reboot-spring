package reboot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reboot.spring.bean.profile.IProfileService;
import reboot.spring.bean.profile.RealProfileService;

@Profile("real")
@Configuration
public class RealProfileConfig {

    @Bean
    public IProfileService profileService() {
        return new RealProfileService();
    }

}
