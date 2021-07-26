package reboot.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /assets/js/jquery.js, /assets/css/bootstrap.css
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/assets/", "/assets/");

        // /js/jquery.js, /css/bootstrap.css
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/assets/", "/assets/");
    }

}
