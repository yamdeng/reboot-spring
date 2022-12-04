package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeWorkHolidayVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HolidayDao {

    // 읽지읂은 알림 count
    int checkHolidayByDateStr(OfficeWorkHolidayVO vo);

}
