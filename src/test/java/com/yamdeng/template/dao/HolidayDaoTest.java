package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.DeptWorkTimeSettingDao;
import com.yamdeng.template.data.dao.HolidayDao;
import com.yamdeng.template.vo.db.OfficeDeptWorkTimeSettingVO;
import com.yamdeng.template.vo.db.OfficeWorkHolidayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class HolidayDaoTest {

	@Autowired
	private HolidayDao holidayDao;

	// 공휴일여부 확인
	@Test
	void checkHolidayByDateStr() {
		int result = holidayDao.checkHolidayByDateStr(
				OfficeWorkHolidayVO.builder()
						.baseYear("2022")
						.holiDateStr("20221205")
						.build()
		);
		log.info("checkHolidayByDateStr result : {}", result);
	}

	// 이전 평일 구하기
	@Test
	void beforeWorkDate() {
		LocalDate today = LocalDate.now();
		int minusDay = 1;
		String beforeWorkDateStr = "";
		while(true) {
			LocalDate beforeDate = today.minusDays(minusDay);
			int weekValue = beforeDate.getDayOfWeek().getValue();
			// 토요일, 일요일 아니고
			if(weekValue != 6 && weekValue != 7) {
				// 공휴일 테이블 확인
				int result = holidayDao.checkHolidayByDateStr(
						OfficeWorkHolidayVO.builder()
								.baseYear("2022")
								.holiDateStr("20221205")
								.build()
				);
				if(result < 1) {
					beforeWorkDateStr = beforeDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
					break;
				}
			}
			minusDay++;
		}
		log.info("beforeWorkDate result : {}", beforeWorkDateStr);
	}

	// 공휴일 list
	@Test
	void selectWorkHolidayList() {
		OfficeWorkHolidayVO vo = OfficeWorkHolidayVO.builder()
//				.baseYear("2022")
				.startDateStr("20221201")
				.endDateStr("20221231")
				.build();
		List<OfficeWorkHolidayVO> result = holidayDao.selectWorkHolidayList(vo);
		int totalCount = holidayDao.selectWorkHolidayListTotalCount(vo);
		log.info("selectWorkHolidayList result : {}", result);
		log.info("selectWorkHolidayList totalCount : {}", totalCount);
	}

}
