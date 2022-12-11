package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.VacationDao;
import com.yamdeng.template.data.dao.VacationPreviewDao;
import com.yamdeng.template.vo.db.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class VacationPreviewDaoTest {

	@Autowired
	private VacationPreviewDao vacationPreviewDao;

	// 휴가_휴직_현황(연별)_미리보기 list
	@Test
	void selectVacationYearPreviewList() {
		OfficeVacationYearPreviewVO vo =
				OfficeVacationYearPreviewVO.builder()
						.baseYear("2022")
						.newEmployeeYn("Y")
						.build();
		List<OfficeVacationYearPreviewVO> result = vacationPreviewDao.selectVacationYearPreviewList(vo);
		int totalCount = vacationPreviewDao.selectVacationYearPreviewListTotalCount(vo);
		log.info("selectVacationYearPreviewList result : {}", result);
		log.info("selectVacationYearPreviewList totalCount : {}", totalCount);
	}

	// 휴가_휴직_현황(연별)_미리보기 detail
	@Test
	void selectVacationYearPreviewInfo() {
		OfficeVacationYearPreviewVO vo =
				OfficeVacationYearPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.build();
		OfficeVacationYearPreviewVO result = vacationPreviewDao.selectVacationYearPreviewInfo(vo);
		log.info("selectVacationYearPreviewInfo result : {}", result);
	}

	// 휴가_휴직_현황(연별)_미리보기 insert
	@Test
	void insertVacationYearPreview() {
		OfficeVacationYearPreviewVO vo =
				OfficeVacationYearPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.annualCount(10.0)
						.monthCount(5.0)
						.useableCount(15.0)
						.plusVacationCount(3.0)
						.usedCount(10.0)
						.build();
		int result = vacationPreviewDao.insertVacationYearPreview(vo);
		log.info("insertVacationYearPreview result : {}", result);
	}

	// 휴가_휴직_현황(연별)_미리보기 update
	@Test
	void updateVacationYearPreview() {
		OfficeVacationYearPreviewVO vo =
				OfficeVacationYearPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.annualCount(10.0)
						.monthCount(5.0)
						.useableCount(15.0)
						.plusVacationCount(3.0)
						.usedCount(10.0)
						.build();
		int result = vacationPreviewDao.updateVacationYearPreview(vo);
		log.info("updateVacationYearPreview result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가)_미리보기 list
	@Test
	void selectVacationPlusPreviewList() {
		OfficeVacationPlusPreviewVO vo =
				OfficeVacationPlusPreviewVO.builder()
						.baseYear("2022")
						.build();
		List<OfficeVacationPlusPreviewVO> result = vacationPreviewDao.selectVacationPlusPreviewList(vo);
		int totalCount = vacationPreviewDao.selectVacationPlusPreviewListTotalCount(vo);
		log.info("selectVacationPlusPreviewList result : {}", result);
		log.info("selectVacationPlusPreviewList totalCount : {}", totalCount);
	}

	// 휴가_휴직_현황(포상휴가)_미리보기 detail
	@Test
	void selectVacationPlusPreviewInfo() {
		OfficeVacationPlusPreviewVO vo =
				OfficeVacationPlusPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.build();
		OfficeVacationPlusPreviewVO result = vacationPreviewDao.selectVacationPlusPreviewInfo(vo);
		log.info("selectVacationPlusPreviewInfo result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가)_미리보기 insert
	@Test
	void insertVacationPlusPreview() {
		OfficeVacationPlusPreviewVO vo =
				OfficeVacationPlusPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.vacationName("포상휴가1")
						.vacationCount(10.0)
						.build();
		int result = vacationPreviewDao.insertVacationPlusPreview(vo);
		log.info("insertVacationPlusPreview result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가)_미리보기 update
	@Test
	void updateVacationPlusPreview() {
		OfficeVacationPlusPreviewVO vo =
				OfficeVacationPlusPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.vacationCount(3.5)
						.build();
		int result = vacationPreviewDao.updateVacationPlusPreview(vo);
		log.info("updateVacationPlusPreview result : {}", result);
	}

	// 휴가_휴직_현황(포상휴가)_미리보기 delete
	@Test
	void deleteVacationPlusPreview() {
		OfficeVacationPlusPreviewVO vo =
				OfficeVacationPlusPreviewVO.builder()
						.baseYear("2022")
						.userId("yamdeng")
						.userIdList(Arrays.asList("yamdeng1", "yamdeng2"))
						.build();
		int result = vacationPreviewDao.deleteVacationPlusPreview(vo);
		log.info("deleteVacationPlusPreview result : {}", result);
	}

}
