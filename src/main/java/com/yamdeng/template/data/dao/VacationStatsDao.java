package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.stats.OfficeCommuteWeekStatsVO;
import com.yamdeng.template.vo.stats.OfficeVacationMonthStatsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationStatsDao {

    // 통계_휴가_월별_사용현황 list
    List<OfficeVacationMonthStatsVO> selectVacationMonthStatsList(OfficeVacationMonthStatsVO vo);

    // 통계_휴가_월별_사용현황 list totalCount
    int selectVacationMonthStatsListTotalCount(OfficeVacationMonthStatsVO vo);

    // 통계_휴가_월별_사용현황 detail
    OfficeVacationMonthStatsVO selectVacationMonthStatsInfo(OfficeVacationMonthStatsVO vo);

    // 통계_휴가_월별_사용현황 insert
    int insertVacationMonthStats(OfficeVacationMonthStatsVO vo);

    // 통계_휴가_월별_사용현황 update
    int updateVacationMonthStats(OfficeVacationMonthStatsVO vo);

}
