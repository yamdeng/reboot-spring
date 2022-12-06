package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDao {

    // 출퇴근 상세 조회 : 사용자ID 기준
    OfficeCommuteDayVO selectCommuteInfoByUserId(OfficeCommuteDayVO vo);

    // 출근
    int startWork(OfficeCommuteDayVO vo);

    // 퇴근
    int outWork(OfficeCommuteDayVO vo);

    // 출퇴근 목록 조회 : 부서키 기준
    List<OfficeCommuteDayVO> selectCommuteListByDeptKey(OfficeCommuteDayVO vo);

    // 출/퇴근 대상 직원 전체 목록
    List<BaseCommonVO> selectCommuteTargetUserList();

    // 출퇴근_일일 insert
    int insertCommute(OfficeCommuteDayVO vo);

    // 출퇴근_일일 update
    int updateCommute(OfficeCommuteDayVO vo);

    // 실원 근무/출퇴근 현황
    List<OfficeCommuteDayVO> selectCommuteListByDeptIdList(OfficeCommuteDayVO vo);

    // {월} 출퇴근 현황 : 개인
    List<StatsCommonVO> selectCommuteStatsTypePrivate(OfficeCommuteDayVO vo);

    // 출퇴근 목록 : 공통
    List<OfficeCommuteDayVO> selectCommuteList(OfficeCommuteDayVO vo);

    // {일} 팀원 출퇴근 현황 : 팀장
    List<StatsCommonVO> selectCommuteStatsDayTypeManager(OfficeCommuteDayVO vo);

    // 부서 출퇴근 반영시 일일_출퇴근 테이블 제출여부 반영완료
    int completeDeptSubmit(OfficeCommuteDayVO vo);

}
