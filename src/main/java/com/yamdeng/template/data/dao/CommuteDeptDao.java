package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeCommuteDeptDayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDeptDao {

    // 부서별 출퇴근 목록 : 공통
    List<OfficeCommuteDeptDayVO> selectCommuteDeptList(OfficeCommuteDeptDayVO vo);

}
