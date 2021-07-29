package reboot.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.properties.CustomProperties;
import reboot.spring.boot.properties.YamdengProperties;

@RestController
@RequestMapping("/api/properties")
public class PropertiesController {

    @Autowired
    private YamdengProperties yamdengProperties;

    @Autowired
    private CustomProperties customProperties;

    @GetMapping("/yamdeng")
    public YamdengProperties yamdengProperties() {
        return yamdengProperties;
    }

    @GetMapping("/custom")
    public CustomProperties customProperties() {
        return customProperties;
    }

}
