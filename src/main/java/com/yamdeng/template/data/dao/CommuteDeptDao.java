package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeCommuteDeptDayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDeptDao {

    // 부서별 출퇴근 목록 : 공통
    List<OfficeCommuteDeptDayVO> selectCommuteDeptList(OfficeCommuteDeptDayVO vo);

    // 부서별 출퇴근 상세 : 공통
    OfficeCommuteDeptDayVO selectCommuteDateInfo(OfficeCommuteDeptDayVO vo);

    // 부서 출퇴근 수정 : [제출] 액션과 동일
    int updateCommuteDept(OfficeCommuteDeptDayVO vo);

    // 출퇴근 관리 현황 : 일, 월, 기간
    List<StatsCommonVO> selectCommuteDeptStatsTypeAdmin(OfficeCommuteDeptDayVO vo);

}
