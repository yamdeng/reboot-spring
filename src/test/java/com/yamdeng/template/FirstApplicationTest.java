package com.yamdeng.template;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BootApplication.class)
class FirstApplicationTest {

	@Test
	void firstTest() {
        assertEquals("yamdeng", "yamdeng");
	}

}
