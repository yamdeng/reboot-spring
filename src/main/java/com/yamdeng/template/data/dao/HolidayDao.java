package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeWorkHolidayVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HolidayDao {

    // 공휴일여부 확인
    int checkHolidayByDateStr(OfficeWorkHolidayVO vo);

}
