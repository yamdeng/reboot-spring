package com.yamdeng.template;

import com.yamdeng.template.data.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class FirstApplicationTest {

	@Autowired
	private UserDao userDao;

	@Test
	void firstTest() {
        assertEquals("yamdeng", "yamdeng");
		userDao.test();
	}

}
