package reboot.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reboot.spring.boot.util.HttpClientUtil;

@SpringBootTest
class BootApplicationTests {

	@Autowired
	private HttpClientUtil httpClientUtil;

	@Test
	void contextLoads() {
		httpClientUtil.test();
	}

}
