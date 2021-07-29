package reboot.spring.boot.platform.license;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.SpringVersion;

@Slf4j
public class LicenseCheckByReal implements LicenseCheck {

    @Value("${license.version}")
    private String version;

    @Override
    public String currentVersion() {
        String result = "spring version : " + SpringVersion.getVersion() + ", application version : " + version;
        return result;
    }

    @Override
    public void init() {
        log.info("license check start real environment!!!");
    }

    @Override
    public void destroy() {
        log.info("license check close dev environment!!!");
    }
}
