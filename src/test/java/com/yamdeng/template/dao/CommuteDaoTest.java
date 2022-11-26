package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.UserDao;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class CommuteDaoTest {

	@Autowired
	private CommuteDao commuteDao;

	@Test
	void test() {
		List<OfficeCommuteDayVO> list = commuteDao.test();
		log.info("list : {}", list);
	}

	@Test
	void selectByBaseDateStr() {
		OfficeCommuteDayRequestVO vo =
				OfficeCommuteDayRequestVO.builder()
						.baseDateStr("20221126")
						.build();
		List<OfficeCommuteDayVO> list = commuteDao.selectByBaseDateStr(vo);
		log.info("list : {}", list);
	}

}
