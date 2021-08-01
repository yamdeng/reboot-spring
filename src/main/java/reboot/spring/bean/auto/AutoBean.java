package reboot.spring.bean.auto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Data
public class AutoBean {

    private String beanName = "autoBean";

    public AutoBean() {
        System.out.println("AutoBean 생성자");
    }

    @PostConstruct
    public void init() {
        System.out.println("AutoBean init call");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AutoBean destroy call");
    }

}
