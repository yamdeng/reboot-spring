package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.PortalStatsDao;
import com.yamdeng.template.data.dao.UserDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.common.StatsCommonVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class PortalStatsDaoTest {

	@Autowired
	private PortalStatsDao portalStatsDao;

	// 포탈 실장의 통계 정보
	@Test
	void selectPortalStatsByHeader() {
		List<StatsCommonVO> result = portalStatsDao.selectPortalStatsByHeader(
				BaseCommonVO.builder()
						.searchDateStr("20221203")
						.beforeWorkDateStr("20221202")
						.childDeptIdList(Arrays.asList("dept1"))
						.build()
		);
		log.info("selectPortalStatsByHeader result : {}", result);
	}

	// 포탈 관리자 > 전체현황 통계 정보
	@Test
	void selectPortalStatsAdminTypeAll() {
		List<StatsCommonVO> result = portalStatsDao.selectPortalStatsAdminTypeAll(
				BaseCommonVO.builder()
						.searchDateStr("20221203")
						.build()
		);
		log.info("selectPortalStatsAdminByAll result : {}", result);
	}

	// 포탈 관리자 > 출퇴근제출 통계 정보
	@Test
	void selectPortalStatsAdminTypeCommuteSubmit() {
		List<StatsCommonVO> result = portalStatsDao.selectPortalStatsAdminTypeCommuteSubmit(
				BaseCommonVO.builder()
						.searchDateStr("20221203")
						.build()
		);
		log.info("selectPortalStatsAdminTypeCommuteSubmit result : {}", result);
	}

	// 포탈 관리자 > 업무보고 통계 정보
	@Test
	void selectPortalStatsAdminTypeWorkReport() {
		List<StatsCommonVO> result = portalStatsDao.selectPortalStatsAdminTypeWorkReport(
				BaseCommonVO.builder()
						.searchDateStr("20221203")
						.build()
		);
		log.info("selectPortalStatsAdminTypeWorkReport result : {}", result);
	}

}
