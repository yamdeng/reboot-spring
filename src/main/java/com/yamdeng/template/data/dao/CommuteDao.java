package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.request.OfficeCommuteDayRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDao {

    // 출퇴근 상세 조회 : 사용자ID 기준
    OfficeCommuteDayVO selectCommuteInfoByUserId(OfficeCommuteDayRequestVO vo);

    // 출근
    int startWork(OfficeCommuteDayRequestVO vo);

    // 퇴근
    int outWork(OfficeCommuteDayRequestVO vo);

    // 출퇴근 목록 조회 : 부서키 기준
    List<OfficeCommuteDayVO> selectCommuteListByDeptKey(OfficeCommuteDayRequestVO vo);

    // 출/퇴근 대상 직원 전체 목록
    List<BaseCommonVO> selectCommuteAllUserList();

}
