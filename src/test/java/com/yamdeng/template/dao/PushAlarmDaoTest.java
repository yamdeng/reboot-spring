package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.PushAlarmDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficePushAlarmVO;
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

	// 읽지않은 알림 count
	@Test
	void unreadCountByUserId() {
		int result = pushAlarmDao.unreadCountByUserId("yamdeng");
		log.info("unreadCountByUserId result : {}", result);
	}

	// 이미 알림 테이블에 저장하였는지 확인
	@Test
	void checkAlreadySave() {
		OfficePushAlarmVO vo =
				OfficePushAlarmVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.alarmKindCode(Constant.CODE_ALARM_COMMUTE_PRIVATE)
						.build();
		int result = pushAlarmDao.checkAlreadySave(vo);
		log.info("checkHolidayByDateStr result : {}", result);
	}

	@Test
	void insertPushAlarm() {
		OfficePushAlarmVO vo =
				OfficePushAlarmVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.alarmKindCode(Constant.CODE_ALARM_COMMUTE_PRIVATE)
						.alarmTitle("test")
						.build();
		int result = pushAlarmDao.insertPushAlarm(vo);
		log.info("insertPushAlarm result : {}", result);
	}

}
