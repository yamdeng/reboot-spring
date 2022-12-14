package com.yamdeng.template.dao;

import com.yamdeng.template.BootStandardApplication;
import com.yamdeng.template.data.dao.WorkReportDao;
import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeWorkReportCommentVO;
import com.yamdeng.template.vo.db.OfficeWorkReportVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = BootStandardApplication.class)
@Slf4j
class WorkReportDaoTest {

	@Autowired
	private WorkReportDao workReportDao;

	// 업무보고 list
	@Test
	void selectWorkReportList() {
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.baseDateStr("20221203")
						.twoBeforeWorkDateStr("20221203")
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.searchKind("COMMENT")
						.build();
		List<OfficeWorkReportVO> result = workReportDao.selectWorkReportList(vo);
		int totalCount = workReportDao.selectWorkReportListTotalCount(vo);
		log.info("selectWorkReportList result : {}", result);
		log.info("selectWorkReportList totalCount : {}", totalCount);
	}

	// 부서ID 기준 일주일 업무보고 list(공휴일 포함)
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

	// 업무보고 list-ByDeptIdList : 부서키 목록 기준
	@Test
	void selectWorkReportListByDeptIdList() {
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.searchDateStr("20221203")
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.build();
		List<OfficeWorkReportVO> result = workReportDao.selectWorkReportListByDeptIdList(vo);
		int totalCount = workReportDao.selectWorkReportListByDeptIdListTotalCount(vo);
		log.info("selectWorkReportListByDeptIdList result : {}", result);
		log.info("selectWorkReportListByDeptIdList totalCount : {}", totalCount);
	}

	// 업무보고 detail : 기준날짜 + 부서 ID 기준
	@Test
	void selectWorkReportInfoByBaseDateStrAndDeptId() {
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.baseDateStr("20221203")
						.deptId("dept1")
						.build();
		OfficeWorkReportVO result = workReportDao.selectWorkReportInfoByBaseDateStrAndDeptId(vo);
		log.info("selectWorkReportInfoByBaseDateStrAndDeptId result : {}", result);
	}

	// 업무보고 detail : report_id 기준
	@Test
	void selectWorkReportInfoByReportId() {
		String reportId = "fcb6fcdf-1aab-41e9-8ead-181dfc05b320";
		OfficeWorkReportVO result = workReportDao.selectWorkReportInfoByReportId(reportId);
		log.info("selectWorkReportInfoByReportId result : {}", result);
	}

	// 업무보고 insert
	@Test
	void insertWorkReport() {
		String reportId = UUID.randomUUID().toString();
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.reportId(reportId)
						.baseDateStr("20221203")
						.deptId("dept1")
						.loginUserId("yamdeng")
						.build();
		int result = workReportDao.insertWorkReport(vo);
		log.info("insertWorkReport result : {}", result);
	}

	// 업무보고 update
	@Test
	void updateWorkReport() {
		String reportId = "fcb6fcdf-1aab-41e9-8ead-181dfc05b320";
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.reportId(reportId)
						.reportContent("content")
						.reportDate(LocalDateTime.now())
						.modDate(LocalDateTime.now())
						.issueYn("Y")
						.loginUserId("yamdeng")
						.build();
		int result = workReportDao.updateWorkReport(vo);
		log.info("updateWorkReport result : {}", result);
	}

	// 업무보고_댓글 detail : report_id 기준
	@Test
	void selectWorkReportCommentInfoByReportId() {
		String reportId = "fcb6fcdf-1aab-41e9-8ead-181dfc05b320";
		OfficeWorkReportCommentVO result = workReportDao.selectWorkReportCommentInfoByReportId(reportId);
		log.info("selectWorkReportCommentInfoByReportId result : {}", result);
	}

	// 업무보고_댓글 detail : comment_id 기준
	@Test
	void selectWorkReportCommentInfoByCommentId() {
		String commentId = "ebc911cb-321d-493c-90d3-a23c7779549f";
		OfficeWorkReportCommentVO result = workReportDao.selectWorkReportCommentInfoByCommentId(commentId);
		log.info("selectWorkReportCommentInfoByCommentId result : {}", result);
	}

	// 업무보고_댓글 insert
	@Test
	void insertWorkReportComment() {
		String reportId = "fcb6fcdf-1aab-41e9-8ead-181dfc05b320";
		String commentId = UUID.randomUUID().toString();
		OfficeWorkReportCommentVO vo =
				OfficeWorkReportCommentVO.builder()
						.commentId(commentId)
						.reportId(reportId)
						.userId("yamdeng")
						.commentContent("댓글 내용")
						.loginUserId("yamdeng")
						.build();
		int result = workReportDao.insertWorkReportComment(vo);
		log.info("insertWorkReportComment result : {}", result);
	}

	// 업무보고_댓글 update
	@Test
	void updateWorkReportComment() {
		String commentId = "ebc911cb-321d-493c-90d3-a23c7779549f";
		OfficeWorkReportCommentVO vo =
				OfficeWorkReportCommentVO.builder()
						.commentId(commentId)
						.commentContent("댓글 내용2")
						.userId("sb")
						.modDate(LocalDateTime.now())
						.loginUserId("yamdeng")
						.build();
		int result = workReportDao.updateWorkReportComment(vo);
		log.info("updateWorkReportComment result : {}", result);
	}

	// 업무보고 현황 : 월간, 하루, 기간
	@Test
	void selectWorkReportStats() {
		OfficeWorkReportVO vo =
				OfficeWorkReportVO.builder()
						.searchDateStr("20221203")
						.childDeptIdList(Arrays.asList("dept1", "dept2"))
						.twoBeforeWorkDateStr("20221203")
						.build();
		List<StatsCommonVO> result = workReportDao.selectWorkReportStats(vo);
		log.info("selectWorkReportStats result : {}", result);
	}

}
