package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDao {

    List<OfficeCommuteDayVO> test();

    List<OfficeCommuteDayVO> selectByBaseDateStr(OfficeCommuteDayRequestVO vo);

    List<OfficeCommuteDayVO> selectByBaseDateStrMergeUser(OfficeCommuteDayRequestVO vo);

}
