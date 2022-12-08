package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.stats.OfficeCommuteMonthDStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthHStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteWeekStatsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteStatsDao {

    // 통계_출퇴근_주간 list
    List<OfficeCommuteWeekStatsVO> selectCommuteStatsWeekList(OfficeCommuteWeekStatsVO vo);

    // 통계_출퇴근_주간 list totalCount
    int selectCommuteStatsWeekListTotalCount(OfficeCommuteWeekStatsVO vo);

    // 통계_출퇴근_월간_주별 list
    List<OfficeCommuteMonthDStatsVO> selectCommuteStatsMonthDList(OfficeCommuteMonthDStatsVO vo);

    // 통계_출퇴근_월간_주별 list totalCount
    int selectCommuteStatsMonthDListTotalCount(OfficeCommuteMonthDStatsVO vo);

    // 통계_출퇴근_월간_휴일 list
    List<OfficeCommuteMonthHStatsVO> selectCommuteStatsMonthHList(OfficeCommuteMonthHStatsVO vo);

    // 통계_출퇴근_월간_휴일 list totalCount
    int selectCommuteStatsMonthHListTotalCount(OfficeCommuteMonthHStatsVO vo);

}
