package reboot.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reboot.spring.bean.FirstDao;
import reboot.spring.bean.FirstRepository;
import reboot.spring.bean.FirstService;
import reboot.spring.bean.IFirstRepository;
import reboot.spring.bean.IFirstService;

@Configuration
public class BeanConfig2 {

    @Bean(initMethod = "initManual", destroyMethod = "destroyManual")
    public FirstDao firstDao() {
        return new FirstDao();
    }

    @Bean
    public IFirstRepository firstRepository() {
        return new FirstRepository();
    }

    @Bean("firstService")
    public IFirstService firstService() {
        return new FirstService(firstRepository());
    }

}
