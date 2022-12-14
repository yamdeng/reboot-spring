package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.stats.OfficeCommuteDeyHeaderStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteDeyManagerStatsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class CommuteDaoTest {

	@Autowired
	private CommuteDao commuteDao;

	// 출퇴근_일일 list
	@Test
	void selectCommuteList() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.searchMonthStr("202212")
						.userId("yamdeng")
						.limit(10)
						.offset(0)
						.build();
		List<OfficeCommuteDayVO> result = commuteDao.selectCommuteList(vo);
		Integer totalCount = commuteDao.selectCommuteListTotalCount(vo);
		log.info("selectCommuteList result : {}", result);
		log.info("selectCommuteListTotalCount totalCount : {}", totalCount);
	}

	// 출퇴근_일일 detail
	@Test
	void selectCommuteInfo() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.build();
		OfficeCommuteDayVO result = commuteDao.selectCommuteInfo(vo);
		log.info("selectCommuteInfo result : {}", result);
	}

	// 출퇴근_일일 insert
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

	// 출퇴근_일일 update
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
		OfficeCommuteDayVO detailInfo = commuteDao.selectCommuteInfo(vo);
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

	// 출퇴근_일일 list-ByDeptKey : 부서키 기준
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
		int totalCount = commuteDao.selectCommuteListTotalCountByDeptKey(vo);
		log.info("selectCommuteListByDeptKey result : {}", result);
		log.info("selectCommuteListByDeptKey totalCount : {}", totalCount);
	}

	// 춭/퇴근 대상 직원 list
	@Test
	void selectCommuteTargetUserList() {
		List<BaseCommonVO> result = commuteDao.selectCommuteTargetUserList();
		int totalCount = commuteDao.selectCommuteTargetUserListTotalCount();
		log.info("selectCommuteTargetUserList result : {}", result);
		log.info("selectCommuteTargetUserList totalCount : {}", totalCount);
	}

	// 출퇴근_일일 list-ByDeptIdList : 부서키 목록 기준
	@Test
	void selectCommuteListByDeptIdList() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.limit(10)
						.build();
		List<OfficeCommuteDayVO> result = commuteDao.selectCommuteListByDeptIdList(vo);
		int totalCount = commuteDao.selectCommuteListByDeptIdListTotalCount(vo);
		log.info("selectCommuteListByDeptIdList result : {}", result);
		log.info("selectCommuteListByDeptIdList totalCount : {}", totalCount);
	}

	// {월} 출퇴근 현황 : 개인
	@Test
	void selectCommuteStatsTypePrivate() {
		List<StatsCommonVO> result = commuteDao.selectCommuteStatsTypePrivate(
				OfficeCommuteDayVO.builder()
								.searchMonthStr("202212")
								.userId("yamdeng")
								.loginUserId("yamdeng")
								.build()
		);
		log.info("selectCommuteStatsTypePrivate result : {}", result);
	}

	// {일} 팀원 출퇴근 현황 : 팀장
	@Test
	void selectCommuteStatsDayTypeManager() {
		List<StatsCommonVO> result = commuteDao.selectCommuteStatsDayTypeManager(
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.build()
		);
		log.info("selectCommuteStatsDayTypeManager result : {}", result);
	}

	// 부서 출퇴근 반영시 일일_출퇴근 테이블 제출여부 반영완료
	@Test
    void completeDeptSubmit() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.baseDateStr("20221203")
						.build();
		int result = commuteDao.completeDeptSubmit(vo);
		log.info("completeDeptSubmit result : {}", result);
	}

	// {월, 기간} 팀원 출퇴근 현황 : 팀장
	@Test
	void selectCommuteStatsMonthTypeManager() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.deptKey("dept1")
//						.searchMonthStr("202212")
						.startDateStr("20221201")
						.endDateStr("20221231")
						.build();
		List<OfficeCommuteDeyManagerStatsVO> result = commuteDao.selectCommuteStatsMonthTypeManager(vo);
		log.info("selectCommuteStatsMonthTypeManager result : {}", result);
	}

	// {월, 기간} 팀원 출퇴근 현황 1 : 실장
	@Test
	void selectCommuteSimpleStatsMonthTypeHeader() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.searchMonthStr("202212")
//						.startDateStr("20221201")
//						.endDateStr("20221231")
						.build();
		List<StatsCommonVO> result = commuteDao.selectCommuteSimpleStatsMonthTypeHeader(vo);
		log.info("selectCommuteSimpleStatsMonthTypeHeader result : {}", result);
	}

	// {월, 기간} 팀원 출퇴근 현황 2 : 실장
	@Test
	void selectCommuteStatsMonthTypeHeader() {
		OfficeCommuteDayVO vo =
				OfficeCommuteDayVO.builder()
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.searchMonthStr("202212")
//						.startDateStr("20221201")
//						.endDateStr("20221231")
						.build();
		List<OfficeCommuteDeyHeaderStatsVO> result = commuteDao.selectCommuteStatsMonthTypeHeader(vo);
		log.info("selectCommuteStatsMonthTypeHeader result : {}", result);
	}

	// 전체 현황 : 하루, 기간
	@Test
	void selectCommuteStatsDayTypeAdmin() {
		List<StatsCommonVO> result = commuteDao.selectCommuteStatsDayTypeAdmin(
				OfficeCommuteDayVO.builder()
						.baseDateStr("20221203")
						.build()
		);
		log.info("selectCommuteStatsDayTypeAdmin result : {}", result);
	}

}
