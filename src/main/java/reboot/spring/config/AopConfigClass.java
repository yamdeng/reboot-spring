package reboot.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import reboot.spring.bean.aop.AfterLogAspect;
import reboot.spring.bean.aop.ExceptionAspect;
import reboot.spring.bean.aop.ExceptionBean;
import reboot.spring.bean.aop.StartLogAspect;
import reboot.spring.bean.aop.SuccessLogAspect;
import reboot.spring.bean.aop.SuccessLogAspect2;
import reboot.spring.bean.aop.TimeCheckAspect;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableAspectJAutoProxy
@Import({BeanConfig2.class})
public class AopConfigClass {

    @Bean
    public TimeCheckAspect timeCheckAspect() {
        return new TimeCheckAspect();
    }

    @Bean
    public StartLogAspect startLogAspect() {
        return new StartLogAspect();
    }

    @Bean
    public SuccessLogAspect successLogAspect() {
        return new SuccessLogAspect();
    }

    @Bean
    public SuccessLogAspect2 successLogAspect2() {
        return new SuccessLogAspect2();
    }

    @Bean
    public AfterLogAspect afterLogAspect() {
        return new AfterLogAspect();
    }

    @Bean
    public ExceptionAspect exceptionAspect() {
        return new ExceptionAspect();
    }

    @Bean
    public ExceptionBean exceptionBean() {
        return new ExceptionBean();
    }

}
