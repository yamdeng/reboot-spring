package com.yamdeng.template;

import com.yamdeng.common.config.RootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.yamdeng.template", "com.yamdeng.template.controller"})
//@ComponentScan(basePackages = {"spring", "spring2" },
//    excludeFilters = {
//        @Filter(type = FilterType.ANNOTATION, classes = BeanNoScan.class )
//    })
@Import({RootConfig.class})
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

}
