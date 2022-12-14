package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeVacationDetailDayHistoryVO;
import com.yamdeng.template.vo.db.OfficeVacationDetailVO;
import com.yamdeng.template.vo.db.OfficeVacationPlusVO;
import com.yamdeng.template.vo.db.OfficeVacationYearVO;
import com.yamdeng.template.vo.stats.OfficeVacationMonthStatsAllVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationDao {

    // 휴가_휴직_현황(연별) list
    List<OfficeVacationYearVO> selectVacationYearList(OfficeVacationYearVO vo);

    // 휴가_휴직_현황(연별) list totalCount
    int selectVacationYearListTotalCount(OfficeVacationYearVO vo);

    // 휴가_휴직_현황(연별) detail : user_id 기준
    OfficeVacationYearVO selectVacationYearInfoByUserId(OfficeVacationYearVO vo);

    // 휴가_휴직_상세 list
    List<OfficeVacationDetailVO> selectVacationDetailList(OfficeVacationDetailVO vo);

    // 휴가_휴직_상세 list totalCount
    int selectVacationDetailListTotalCount(OfficeVacationDetailVO vo);

    // 휴가_휴직_상세(일별히스토리) list
    List<OfficeVacationDetailDayHistoryVO> selectVacationDetailDayHistoryList(OfficeVacationDetailDayHistoryVO vo);

    // 휴가_휴직_상세(일별히스토리) detail
    OfficeVacationDetailDayHistoryVO selectVacationDetailDayHistoryInfo(OfficeVacationDetailDayHistoryVO vo);

    // 휴가_휴직_현황(연별) insert
    int insertVacationYear(OfficeVacationYearVO vo);

    // 휴가_휴직_현황(연별) update
    int updateVacationYear(OfficeVacationYearVO vo);

    // 휴가_휴직_현황(포상휴가) detail
    OfficeVacationPlusVO selectVacationPlusInfoByUserId(OfficeVacationPlusVO vo);

    // 휴가_휴직_현황(포상휴가) insert
    int insertVacationPlus(OfficeVacationPlusVO vo);

    // 휴가_휴직_현황(포상휴가) update
    int updateVacationPlus(OfficeVacationPlusVO vo);

    // 전체 휴가 관리 list
    List<OfficeVacationMonthStatsAllVO> selectVacationMonthStatsAllList(OfficeVacationMonthStatsAllVO vo);

    // 전체 휴가 관리 list totalCount
    int selectVacationMonthStatsAllListTotalCount(OfficeVacationMonthStatsAllVO vo);

}
