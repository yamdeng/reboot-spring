package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeDeptWorkTimeSettingVO;
import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import com.yamdeng.template.vo.db.OfficeWorkReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptWorkTimeSettingDao {

    // 부서별_근무_시간_관리 list
    List<OfficeDeptWorkTimeSettingVO> selectDeptWorkTimeSettingList(OfficeDeptWorkTimeSettingVO vo);

    // 부서별_근무_시간_관리 list totalCount
    int selectDeptWorkTimeSettingListTotalCount(OfficeDeptWorkTimeSettingVO vo);

    // 부서별_근무_시간_관리 detail
    OfficeDeptWorkTimeSettingVO selectDeptWorkTimeSettingInfoByDeptId(String deptId);

    // 부서별_근무_시간_관리 insert
    int insertDeptWorkTimeSetting(OfficeDeptWorkTimeSettingVO vo);

    // 부서별_근무_시간_관리 update
    int updateDeptWorkTimeSetting(OfficeDeptWorkTimeSettingVO vo);

}
