package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
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
class CommuteDaoTest {

	@Autowired
	private CommuteDao commuteDao;

	// 출퇴근 상세 조회 : 사용자ID 기준
	@Test
	void selectCommuteInfoByUserId() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.build();
		OfficeCommuteDayVO result = commuteDao.selectCommuteInfoByUserId(vo);
		log.info("selectCommuteInfoByUserId result : {}", result);
	}

	// 출근
	@Test
	void startWork() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.startWorkIp("172.0.0.1")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.workStatusCode(Constant.CODE_WORK_STATUS_VACATION_MORNING)
						.workResultCode(Constant.CODE_WORK_RESULT_SUCCESS_NORMAL)
						.build();
		int result = commuteDao.startWork(vo);
		log.info("startWork result : {}", result);
	}

	// 퇴근
	@Test
	void outWork() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.outWorkIp("172.0.0.1")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.workStatusCode(Constant.CODE_WORK_STATUS_VACATION_MORNING)
						.workResultCode(Constant.CODE_WORK_RESULT_SUCCESS_NORMAL)
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

	// 출퇴근 목록 조회 : 부서키 기준
	@Test
	void selectCommuteListByDeptKey() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.deptKey("dept1")
						.limit(10)
						.build();
		List<OfficeCommuteDayVO> result = commuteDao.selectCommuteListByDeptKey(vo);
		log.info("selectCommuteListByDeptKey result : {}", result);
	}

	// 출/퇴근 대상 직원 전체 목록
	@Test
	void selectCommuteTargetUserList() {
		List<BaseCommonVO> result = commuteDao.selectCommuteTargetUserList();
		log.info("selectCommuteTargetUserList result : {}", result);
	}

	@Test
	void insertCommute() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.userId("sb")
						.workStatusCode(Constant.CODE_WORK_STATUS_VACATION_MORNING)
						.workResultCode(Constant.CODE_WORK_RESULT_SUCCESS_NORMAL)
						.vacationKindCode(Constant.CODE_VACATION_KIND_VACATION_AFTERNOON)
						.workedTimeValue(0.0)
						.build();
		int result = commuteDao.insertCommute(vo);
		log.info("insertCommute result : {}", result);
	}

	@Test
	void updateCommute() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.workResultCode(Constant.CODE_WORK_RESULT_TARDY)
						.modUserId("yamdeng")
						.build();
		int result = commuteDao.updateCommute(vo);
		log.info("updateCommute result : {}", result);
	}

}
