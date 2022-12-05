package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeDeptWorkTimeSettingVO;
import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptWorkTimeSettingDao {

    // 부서별 근무시간 셋팅 상세정보
    OfficeDeptWorkTimeSettingVO selectDeptWorkTimeSettingInfoByDeptId(String deptId);

}
