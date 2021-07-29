package reboot.spring.boot.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reboot.spring.boot.platform.license.LicenseCheck;
import reboot.spring.boot.platform.license.LicenseCheckByReal;

@Profile("production")
@Configuration
public class RealProfileConfig {

    @Bean
    public LicenseCheck licenseCheck() {
        return new LicenseCheckByReal();
    }

}
