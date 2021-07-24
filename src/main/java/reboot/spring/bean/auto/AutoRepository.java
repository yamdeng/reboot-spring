package reboot.spring.bean.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AutoRepository {

    @Autowired
    private AutoDao autoDao;

    public String getBeanName() {
        return autoDao.getBeanName();
    }

}
