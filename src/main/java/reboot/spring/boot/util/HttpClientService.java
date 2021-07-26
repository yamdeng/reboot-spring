package reboot.spring.boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientService {

    @Autowired
    private RestTemplate restTemplate;

    public void test() {
        restTemplate
            .getForObject("http://localhost:8080/api/members", Object.class);
    }

}
