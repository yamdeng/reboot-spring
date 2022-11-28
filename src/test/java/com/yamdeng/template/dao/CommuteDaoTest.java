package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class CommuteDaoTest {

	@Autowired
	private CommuteDao commuteDao;

	// 출퇴근 상세 조회 : 사용자ID 기준
	@Test
	void selectCommuteInfoByUserId() {
		OfficeCommuteDayRequestVO vo =
				OfficeCommuteDayRequestVO.builder()
						.baseDateStr("20221126")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.build();
		OfficeCommuteDayVO result = commuteDao.selectCommuteInfoByUserId(vo);
		log.info("selectCommuteInfo result : {}", result);
	}

	// 출근
	@Test
	void startWork() {
		OfficeCommuteDayRequestVO vo =
				OfficeCommuteDayRequestVO.builder()
						.baseDateStr("20221126")
						.startWorkIp("172.0.0.1")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.workStatusCode("ING")
						.workResultCode("SUCCESS_NORMAL")
						.build();
		int result = commuteDao.startWork(vo);
		log.info("startWork result : {}", result);
	}

	// 퇴근
	@Test
	void outWork() {
		OfficeCommuteDayRequestVO vo =
				OfficeCommuteDayRequestVO.builder()
						.baseDateStr("20221126")
						.outWorkIp("172.0.0.1")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.workStatusCode("END")
						.workResultCode("SUCCESS_NORMAL")
						.build();
		OfficeCommuteDayVO detailInfo = commuteDao.selectCommuteInfoByUserId(vo);
		LocalDateTime startWorkDate = detailInfo.getStartWorkDate();
		LocalDateTime now = LocalDateTime.now();
		long minutes = startWorkDate.until( now, ChronoUnit.MINUTES );
		double minuteValue = (double)minutes / (double)60;
		double workedTimeValue = Math.ceil(minuteValue * 10) / 10.0;
		log.info("workedTimeValue : {}", workedTimeValue);
		log.info("outWork minutes : {}", minutes);
		vo.setWorkedTimeValue(workedTimeValue);
		int result = commuteDao.outWork(vo);
		log.info("outWork result : {}", result);
	}


}
