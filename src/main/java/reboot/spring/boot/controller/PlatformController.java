package reboot.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.platform.license.LicenseCheck;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    @Autowired(required = false)
    private LicenseCheck licenseCheck;

    @GetMapping("/license")
    public Map<String, String> licenseInfo() {
        Map<String, String> licenseMap = new HashMap<>();
        licenseMap.put("version", licenseCheck.currentVersion());
        return licenseMap;
    }


}
