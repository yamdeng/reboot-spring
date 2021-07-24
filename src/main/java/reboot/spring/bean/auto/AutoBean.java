package reboot.spring.bean.auto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AutoBean {

    private String beanName = "autoBean";

    public AutoBean() {
        System.out.println("AutoBean 생성자2");
    }

    @PostConstruct
    public void init() {
        System.out.println("FirstBean init call");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("FirstBean destroy call");
    }

}
