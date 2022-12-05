package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.WorkReportDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeWorkReportVO;
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
class WorkReportDaoTest {

	@Autowired
	private WorkReportDao workReportDao;

	// 부서ID 기준 일주일 업무보고 목록(공휴일 포함)
	@Test
	void selectRecent7DayListByDeptKey() {
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.startDateStr("20221203")
						.endDateStr("20221205")
						.deptId("dept1")
						.build();
		List<OfficeWorkReportVO> result = workReportDao.selectRecent7DayListByDeptId(vo);
		log.info("selectRecent7DayListByDeptKey result : {}", result);
	}

	// 업무보고 목록 : 부서키 목록 기준
	@Test
	void selectWorkReportListByDeptIdList() {
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.baseDateStr("20221203")
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.build();
		List<OfficeWorkReportVO> result = workReportDao.selectWorkReportListByDeptIdList(vo);
		log.info("selectWorkReportListByDeptIdList result : {}", result);
	}

}
