package reboot.spring.boot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reboot.spring.boot.properties.CustomProperties;
import reboot.spring.boot.properties.YamdengProperties;

@Slf4j
@RestController
@RequestMapping("/api/properties")
public class PropertiesController {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${custom.prop1}")
    private String customProp1;

    @Autowired
    private YamdengProperties yamdengProperties;

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private Environment environment;

    @GetMapping("/yamdeng")
    public YamdengProperties yamdengProperties() {
        return yamdengProperties;
    }

    @GetMapping("/custom")
    public CustomProperties customProperties() {
        return customProperties;
    }

    @GetMapping("/value")
    public Map<String, Object> valueProObjectMap() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("datasourceUrl", datasourceUrl);
        resultMap.put("customProp1", customProp1);
        String[] profiles = environment.getActiveProfiles();
        String profileNames = Arrays.toString(profiles);
        resultMap.put("current profiles", profileNames);
        return resultMap;
    }

}
