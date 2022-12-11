package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.VacationDao;
import com.yamdeng.template.vo.db.*;
import com.yamdeng.template.vo.stats.OfficeVacationMonthStatsAllVO;
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
	void selectVacationYearInfoByUserId() {
		OfficeVacationYearVO vo =
				OfficeVacationYearVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.loginUserId("yamdeng")
						.build();
		OfficeVacationYearVO result = vacationDao.selectVacationYearInfoByUserId(vo);
		log.info("selectVacationYearInfoByUserId result : {}", result);
	}

	// 해당일에 휴가가 존재하는지 체크
	@Test
	void selectVacationDetailDayHistoryInfo() {
		OfficeVacationDetailDayHistoryVO vo =
				OfficeVacationDetailDayHistoryVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.build();
		OfficeVacationDetailDayHistoryVO result = vacationDao.selectVacationDetailDayHistoryInfo(vo);
		log.info("selectVacationDetailDayHistoryInfo : {}", result);
	}

	// 휴가이력 목록 : 공통
	@Test
	void selectVacationDetailDayHistoryList() {
		OfficeVacationDetailDayHistoryVO vo =
				OfficeVacationDetailDayHistoryVO.builder()
						.baseDateStr("20221203")
						.userId("yamdeng")
						.build();
		List<OfficeVacationDetailDayHistoryVO> result = vacationDao.selectVacationDetailDayHistoryList(vo);
		log.info("selectVacationDetailDayHistoryList : {}", result);
	}

	// 휴가_휴직_상세 list
	@Test
	void selectVacationDetailList() {
		OfficeVacationDetailVO vo =
				OfficeVacationDetailVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.build();
		List<OfficeVacationDetailVO> result = vacationDao.selectVacationDetailList(vo);
		int totalCount = vacationDao.selectVacationDetailListTotalCount(vo);
		log.info("selectVacationDetailList result : {}", result);
		log.info("selectVacationDetailList totalCount : {}", totalCount);
	}

	// 휴가_휴직_현황(연별) list
	@Test
	void selectVacationYearList() {
		OfficeVacationYearVO vo =
				OfficeVacationYearVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.build();
		List<OfficeVacationYearVO> result = vacationDao.selectVacationYearList(vo);
		int totalCount = vacationDao.selectVacationYearListTotalCount(vo);
		log.info("selectVacationYearList result : {}", result);
		log.info("selectVacationYearList totalCount : {}", totalCount);
	}

	// 휴가_휴직_현황(연별) insert
	@Test
	void insertVacationYear() {
		OfficeVacationYearVO vo =
				OfficeVacationYearVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.annualCount(15.0)
						.monthCount(0.0)
						.useableCount(15.0)
						.plusVacationCount(1.0)
						.usedCount(0.0)
						.build();
		int result = vacationDao.insertVacationYear(vo);
		log.info("insertVacationYear result : {}", result);
	}

	// 휴가_휴직_현황(연별) update
	@Test
	void updateVacationYear() {
		OfficeVacationYearVO vo =
				OfficeVacationYearVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.annualCount(15.0)
						.monthCount(0.0)
						.useableCount(15.0)
						.plusVacationCount(1.0)
						.usedCount(0.0)
						.build();
		int result = vacationDao.updateVacationYear(vo);
		log.info("updateVacationYear result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가) detail
	@Test
	void selectVacationPlusInfoByUserId() {
		OfficeVacationPlusVO vo =
				OfficeVacationPlusVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.build();
		OfficeVacationPlusVO result = vacationDao.selectVacationPlusInfoByUserId(vo);
		log.info("selectVacationPlusInfoByUserId result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가) insert
	@Test
	void insertVacationPlus() {
		OfficeVacationPlusVO vo =
				OfficeVacationPlusVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.vacationName("포상휴가1")
						.vacationCount(10.0)
						.build();
		int result = vacationDao.insertVacationPlus(vo);
		log.info("insertVacationPlus result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가) update
	@Test
	void updateVacationPlus() {
		OfficeVacationPlusVO vo =
				OfficeVacationPlusVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.vacationName("포상휴가1")
						.vacationCount(10.0)
						.build();
		int result = vacationDao.updateVacationPlus(vo);
		log.info("updateVacationPlus result : {}", result);
	}

	// 전체 휴가 관리 list
	@Test
	void selectVacationMonthStatsAllList() {
		OfficeVacationMonthStatsAllVO vo =
				OfficeVacationMonthStatsAllVO.builder()
						.baseYear("2022")
						.build();
		List<OfficeVacationMonthStatsAllVO> result = vacationDao.selectVacationMonthStatsAllList(vo);
		int totalCount = vacationDao.selectVacationMonthStatsAllListTotalCount(vo);
		log.info("selectVacationMonthStatsAllList result : {}", result);
		log.info("selectVacationMonthStatsAllList totalCount : {}", totalCount);
	}

}
