package reboot.spring.bean.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoDao {

    @Autowired
    private AutoBean autoBean;

    public AutoDao() {
        System.out.println("AutoDao 생성자");
    }

    public String getBeanName() {
        return autoBean.getBeanName();
    }

}
