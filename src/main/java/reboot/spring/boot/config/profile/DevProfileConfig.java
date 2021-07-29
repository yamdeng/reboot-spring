package reboot.spring.boot.config.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reboot.spring.boot.platform.license.LicenseCheck;
import reboot.spring.boot.platform.license.LicenseCheckByDev;

@Profile("development")
@Configuration
public class DevProfileConfig{

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LicenseCheck licenseCheck() {
        return new LicenseCheckByDev();
    }

}
