package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.VacationDao;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeVacationYearVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class VacationDaoTest {

	@Autowired
	private VacationDao vacationDao;

	// 휴가/휴직(연별) 상세 조회 : 사용자ID 기준
	@Test
	void selectVacationInfoByUserId() {
		OfficeVacationYearVO vo =
				OfficeVacationYearVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.build();
		OfficeVacationYearVO result = vacationDao.selectVacationInfoByUserId(vo);
		log.info("selectVacationInfoByUserId result : {}", result);
	}

}
