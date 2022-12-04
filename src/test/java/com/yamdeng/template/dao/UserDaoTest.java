package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class UserDaoTest {

	@Autowired
	private UserDao userDao;

	// 부서의 팀장 userId 가져오기
	@Test
	void getDeptManagerUserId() {
		String result = userDao.getDeptManagerUserId("dept1");
		log.info("getDeptManagerUserId result : {}", result);
	}

}
