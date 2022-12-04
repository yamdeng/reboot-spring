package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeDeptWorkTimeSettingVO;
import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptWorkTimeSettingDao {

    OfficeDeptWorkTimeSettingVO selectDeptWorkTimeSettingInfoByDeptId(String deptId);

}
