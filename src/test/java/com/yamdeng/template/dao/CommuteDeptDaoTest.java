package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.CommuteDeptDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeCommuteDeptDayVO;
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
class CommuteDeptDaoTest {

	@Autowired
	private CommuteDeptDao commuteDeptDao;

	// 부서별 출퇴근 목록 : 공통
	@Test
	void selectCommuteDeptList() {
		OfficeCommuteDeptDayVO vo =
				OfficeCommuteDeptDayVO.builder()
						.searchDateStr("20221203")
						.limit(10)
						.offset(0)
						.build();
		List<OfficeCommuteDeptDayVO> result = commuteDeptDao.selectCommuteDeptList(vo);
		log.info("selectCommuteDeptList result : {}", result);
	}

	// 부서별 출퇴근 상세 : 공통
	@Test
	void selectCommuteDateInfo() {
		OfficeCommuteDeptDayVO vo =
				OfficeCommuteDeptDayVO.builder()
						.baseDateStr("20221203")
						.deptId("dept1")
						.build();
		OfficeCommuteDeptDayVO result = commuteDeptDao.selectCommuteDateInfo(vo);
		log.info("selectCommuteDateInfo result : {}", result);
	}

	// 부서 출퇴근 수정 : [제출] 액션과 동일
	@Test
    void updateCommuteDept() {
		OfficeCommuteDeptDayVO vo =
				OfficeCommuteDeptDayVO.builder()
						.userId("yamdeng")
						.deptId("dept1")
						.baseDateStr("20221203")
						.submitDate(LocalDateTime.now())
						.tardyYn("Y")
						.commuteSubmitStatusCode("SUBMIT")
						.targetCount(10)
						.startWorkCompleteCount(10)
						.outWorkCompleteCount(10)
						.build();
		int result = commuteDeptDao.updateCommuteDept(vo);
		log.info("updateCommuteDept result : {}", result);
	}

}
