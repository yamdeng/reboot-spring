package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.VacationDao;
import com.yamdeng.template.data.dao.VacationPreviewDao;
import com.yamdeng.template.vo.db.OfficeVacationDetailDayHistoryVO;
import com.yamdeng.template.vo.db.OfficeVacationDetailVO;
import com.yamdeng.template.vo.db.OfficeVacationYearPreviewVO;
import com.yamdeng.template.vo.db.OfficeVacationYearVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
