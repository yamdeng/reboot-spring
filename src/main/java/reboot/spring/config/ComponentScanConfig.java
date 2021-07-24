package reboot.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import reboot.spring.bean.auto.NotScan;

@Configuration
@ComponentScan(value = "reboot.spring.bean.auto",
    excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = NotScan.class)})
public class ComponentScanConfig {
}
