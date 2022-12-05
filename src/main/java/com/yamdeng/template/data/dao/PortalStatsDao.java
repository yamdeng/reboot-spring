package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortalStatsDao {

    // 포탈 실장일 경우 통계
    List<StatsCommonVO> selectPortalStatsByHeader(BaseCommonVO vo);

}
