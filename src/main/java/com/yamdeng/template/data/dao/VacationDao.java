package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeVacationDetailDayHistoryVO;
import com.yamdeng.template.vo.db.OfficeVacationDetailVO;
import com.yamdeng.template.vo.db.OfficeVacationPlusVO;
import com.yamdeng.template.vo.db.OfficeVacationYearVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationDao {

    // 휴가_휴직_현황(연별) detail : user_id 기준
    OfficeVacationYearVO selectVacationYearInfoByUserId(OfficeVacationYearVO vo);

    // 기준일 기준으로 휴가내역이 존재하는지 체크
    OfficeVacationDetailDayHistoryVO selectVacationDetailDayHistoryInfo(OfficeVacationDetailDayHistoryVO vo);

    // 휴가이력 목록 : 공통
    List<OfficeVacationDetailDayHistoryVO> selectVacationDetailDayHistoryList(OfficeVacationDetailDayHistoryVO vo);

    // 휴가_휴직_상세 list
    List<OfficeVacationDetailVO> selectVacationDetailList(OfficeVacationDetailVO vo);

    // 휴가_휴직_상세 list totalCount
    int selectVacationDetailListTotalCount(OfficeVacationDetailVO vo);

    // 휴가_휴직_현황(연별) list
    List<OfficeVacationYearVO> selectVacationYearList(OfficeVacationYearVO vo);

    // 휴가_휴직_현황(연별) list totalCount
    int selectVacationYearListTotalCount(OfficeVacationYearVO vo);

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

}
