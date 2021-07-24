package reboot.spring.bean.auto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AutoBean {

    private String beanName = "autoBean";

}
