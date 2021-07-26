package reboot.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reboot.spring.boot.interceptor.ApiLogInterceptor;
import reboot.spring.boot.interceptor.ViewLogInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /assets/js/jquery.js, /assets/css/bootstrap.css
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/assets/", "/assets/");

        // /js/jquery.js, /css/bootstrap.css : /** 속성을 오버라이드 하므로 유의
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/assets/", "/assets/", "classpath:/public/", "classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLogInterceptor())
            .addPathPatterns("/api/**");
        registry.addInterceptor(viewLogInterceptor())
            .addPathPatterns("/view/**");
    }

    @Bean
    public ApiLogInterceptor apiLogInterceptor() {
        return new ApiLogInterceptor();
    }

    @Bean
    public ViewLogInterceptor viewLogInterceptor() {
        return new ViewLogInterceptor();
    }

}
