package com.yamdeng.template;

import com.yamdeng.template.data.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BootStandardApplication.class)
class FirstApplicationTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	void firstTest() {
        assertEquals("yamdeng", "yamdeng");
		userMapper.test();
	}

}
