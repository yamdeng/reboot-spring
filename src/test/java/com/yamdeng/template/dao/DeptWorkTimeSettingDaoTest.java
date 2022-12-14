package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.DeptWorkTimeSettingDao;
import com.yamdeng.template.data.dao.PushAlarmDao;
import com.yamdeng.template.vo.db.OfficeDeptWorkTimeSettingVO;
import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class DeptWorkTimeSettingDaoTest {

	@Autowired
	private DeptWorkTimeSettingDao deptWorkTimeSettingDao;

	// 부서별_근무_시간_관리 list
	@Test
	void selectDeptWorkTimeSettingList() {
		OfficeDeptWorkTimeSettingVO vo =
			OfficeDeptWorkTimeSettingVO.builder()
					.build();
		List<OfficeDeptWorkTimeSettingVO> result = deptWorkTimeSettingDao.selectDeptWorkTimeSettingList(vo);
		int totalCount = deptWorkTimeSettingDao.selectDeptWorkTimeSettingListTotalCount(vo);
		log.info("selectDeptWorkTimeSettingList result : {}", result);
		log.info("selectDeptWorkTimeSettingList totalCount : {}", totalCount);
	}

	// 부서별 근무시간 상세 정보 조회
	@Test
	void selectDeptWorkTimeSettingInfoByDeptId() {
		OfficeDeptWorkTimeSettingVO result = deptWorkTimeSettingDao.selectDeptWorkTimeSettingInfoByDeptId("dept1");
		log.info("selectDeptWorkTimeSettingInfoByDeptId result : {}", result);
	}

	// 부서별_근무_시간_관리 insert
	@Test
	void insertDeptWorkTimeSetting() {
		OfficeDeptWorkTimeSettingVO vo =
				OfficeDeptWorkTimeSettingVO.builder()
						.deptId("dept1")
						.timeName("시간명")
						.timeDescription("설명")
						.workLocation("근무지")
						.workStartTime("12:00")
						.workEndTime("18:00")
						.lunchStartTime("12:00")
						.lunchEndTime("13:00")
						.applyStartDateStr("20221201")
						.applyEndDateStr("")
						.build();
		int result = deptWorkTimeSettingDao.insertDeptWorkTimeSetting(vo);
		log.info("insertDeptWorkTimeSetting result : {}", result);
	}

	// 부서별_근무_시간_관리 update
	@Test
	void updateDeptWorkTimeSetting() {
		OfficeDeptWorkTimeSettingVO vo =
				OfficeDeptWorkTimeSettingVO.builder()
						.deptId("dept1")
						.timeName("시간명2")
						.timeDescription("설명2")
						.workLocation("근무지2")
						.workStartTime("12:01")
						.workEndTime("18:01")
						.lunchStartTime("12:01")
						.lunchEndTime("13:01")
						.applyStartDateStr("20221201")
						.applyEndDateStr("")
						.loginUserId("yamdeng")
						.build();
		int result = deptWorkTimeSettingDao.updateDeptWorkTimeSetting(vo);
		log.info("updateDeptWorkTimeSetting result : {}", result);
	}

}
