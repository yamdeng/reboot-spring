package reboot.spring.bean.auto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AutoBean {

    private String beanName = "autoBean";

    public AutoBean() {
        System.out.println("AutoBean 생성자2");
    }

}
