package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.StatsCommonVO;
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

}
