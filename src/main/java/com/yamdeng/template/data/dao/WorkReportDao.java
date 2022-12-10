package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeWorkReportCommentVO;
import com.yamdeng.template.vo.db.OfficeWorkReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkReportDao {

    // 부서ID 기준 일주일 업무보고 목록(공휴일 포함)
    List<OfficeWorkReportVO> selectRecent7DayListByDeptId(OfficeWorkReportVO vo);

    // 업무보고 목록 : 부서키 목록 기준
    List<OfficeWorkReportVO> selectWorkReportListByDeptIdList(OfficeWorkReportVO vo);

    // 업무보고 list
    List<OfficeWorkReportVO> selectWorkReportList(OfficeWorkReportVO vo);

    // 업무보고 list totalCount
    int selectWorkReportListTotalCount(OfficeWorkReportVO vo);

    // 업무보고 현황 : 월간, 하루, 기간
    List<StatsCommonVO> selectWorkReportStats(OfficeWorkReportVO vo);

    // 업무보고 상세 : 기준날짜 + 부서 ID 기준
    OfficeWorkReportVO selectWorkReportInfoByBaseDateStrAndDeptId(OfficeWorkReportVO vo);

    // 업무보고 상세 : report_id 기준
    OfficeWorkReportVO selectWorkReportInfoByReportId(OfficeWorkReportVO vo);

    // 업무보고 insert
    int insertWorkReport(OfficeWorkReportVO vo);

    // 업무보고 update
    int updateWorkReport(OfficeWorkReportVO vo);

    // 업무보고 댓글 상세 조회 : report_id 기준
    OfficeWorkReportCommentVO selectWorkReportCommentInfoByReportId(OfficeWorkReportCommentVO vo);

    // 업무보고 댓글 상세 조회 : comment_id 기준
    OfficeWorkReportCommentVO selectWorkReportCommentInfoByCommentId(OfficeWorkReportCommentVO vo);

    // 업무보고 댓글 insert
    int insertWorkReportComment(OfficeWorkReportCommentVO vo);

    // 업무보고 댓글 update
    int updateWorkReportComment(OfficeWorkReportCommentVO vo);

}
