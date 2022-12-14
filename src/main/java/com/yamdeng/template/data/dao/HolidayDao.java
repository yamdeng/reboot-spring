package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeWorkHolidayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayDao {

    // 공휴일 list
    List<OfficeWorkHolidayVO> selectWorkHolidayList(OfficeWorkHolidayVO vo);

    // 공휴일 list totalCount
    int selectWorkHolidayListTotalCount(OfficeWorkHolidayVO vo);


    // 공휴일여부 확인
    int checkHolidayByDateStr(OfficeWorkHolidayVO vo);

}
