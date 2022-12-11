package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeCommuteDeptDayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDeptDao {

    // 부서별_출퇴근제출 list
    List<OfficeCommuteDeptDayVO> selectCommuteDeptList(OfficeCommuteDeptDayVO vo);

    // 부서별_출퇴근제출 list totalCount
    int selectCommuteDeptListTotalCount(OfficeCommuteDeptDayVO vo);

    // 부서별_출퇴근제출 detail
    OfficeCommuteDeptDayVO selectCommuteDeptInfo(OfficeCommuteDeptDayVO vo);

    // 부서별_출퇴근제출 insert
    int insertCommuteDept(OfficeCommuteDeptDayVO vo);

    // 부서별_출퇴근제출 update : [제출] 액션과 동일, [승인], [반려]도 같이 반영
    int updateCommuteDept(OfficeCommuteDeptDayVO vo);

    // 출퇴근 관리 현황 stats
    List<StatsCommonVO> selectCommuteDeptStatsTypeAdmin(OfficeCommuteDeptDayVO vo);

}
