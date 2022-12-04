package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeVacationDetailDayHistoryVO;
import com.yamdeng.template.vo.db.OfficeVacationYearVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationDao {

    OfficeVacationYearVO selectVacationInfoByUserId(OfficeVacationYearVO vo);

    OfficeVacationDetailDayHistoryVO selectVacationDetailDayHistoryInfo(OfficeVacationDetailDayHistoryVO vo);

}
