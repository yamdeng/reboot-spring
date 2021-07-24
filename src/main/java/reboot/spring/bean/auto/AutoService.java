package reboot.spring.bean.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public String getBeanName() {
        return autoRepository.getBeanName();
    }

}
