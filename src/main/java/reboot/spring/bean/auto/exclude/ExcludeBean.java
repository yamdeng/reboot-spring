package reboot.spring.bean.auto.exclude;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Data
public class ExcludeBean {

    private String beanName = "excludeBean";

    public ExcludeBean() {
        System.out.println("ExcludeBean 생성자");
    }

    @PostConstruct
    public void init() {
        System.out.println("ExcludeBean init call");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ExcludeBean destroy call");
    }

}
