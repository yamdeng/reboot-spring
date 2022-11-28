package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommuteDao {

    // 출퇴근 상세 조회 : 사용자ID 기준
    OfficeCommuteDayVO selectCommuteInfoByUserId(OfficeCommuteDayRequestVO vo);

    int startWork(OfficeCommuteDayRequestVO vo);

    int outWork(OfficeCommuteDayRequestVO vo);

}
