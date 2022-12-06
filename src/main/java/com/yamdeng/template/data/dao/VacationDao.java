package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeVacationDetailDayHistoryVO;
import com.yamdeng.template.vo.db.OfficeVacationYearVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationDao {

    // 휴가/휴직(연별) 상세 조회 : 사용자ID 기준
    OfficeVacationYearVO selectVacationInfoByUserId(OfficeVacationYearVO vo);

    // 기준일 기준으로 휴가내역이 존재하는지 체크
    OfficeVacationDetailDayHistoryVO selectVacationDetailDayHistoryInfo(OfficeVacationDetailDayHistoryVO vo);

    // 휴가이력 목록 : 공통
    List<OfficeVacationDetailDayHistoryVO> selectVacationDetailDayHistoryList(OfficeVacationDetailDayHistoryVO vo);

}
