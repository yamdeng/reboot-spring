package reboot.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reboot.spring.bean.FirstDao;
import reboot.spring.bean.FirstServiceImpl;

@Configuration
public class BeanConfig1 {

    @Bean("firstServiceImpl")
    public FirstServiceImpl firstService() {
        return new FirstServiceImpl(new FirstDao());
    }

}
