package reboot.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import reboot.spring.boot.util.HttpClientUtil;

@TestPropertySource("classpath:/test.properties")
@SpringBootTest(classes = BootApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BootApplicationTests {

	@Autowired
	private HttpClientUtil httpClientUtil;

	@Test
	void contextLoads() {
		httpClientUtil.test();
	}

}
