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

}
