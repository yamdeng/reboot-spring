package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.PushAlarmDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class PushAlarmDaoTest {

	@Autowired
	private PushAlarmDao pushAlarmDao;

	// 출퇴근 상세 조회 : 사용자ID 기준
	@Test
	void unreadCountByUserId() {
		int result = pushAlarmDao.unreadCountByUserId("yamdeng");
		log.info("unreadCountByUserId result : {}", result);
	}

}
