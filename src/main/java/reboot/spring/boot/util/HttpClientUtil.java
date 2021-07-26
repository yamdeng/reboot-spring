package reboot.spring.boot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HttpClientUtil {

    @Autowired
    private RestTemplate restTemplate;

    public void test() {
        String naverResponseResult = restTemplate
            .getForObject("https://www.naver.com", String.class);
        log.info("naverResponseResult : " + naverResponseResult);
    }

    public void membersCall() {
        restTemplate
            .getForObject("http://localhost:8080/api/members", Object.class);
    }

}
