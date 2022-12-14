package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.common.StatsCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.stats.OfficeCommuteDeyHeaderStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteDeyManagerStatsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommuteDao {

    // 출퇴근_일일 detail
    OfficeCommuteDayVO selectCommuteInfo(OfficeCommuteDayVO vo);

    // 출근
    int startWork(OfficeCommuteDayVO vo);

    // 퇴근
    int outWork(OfficeCommuteDayVO vo);

    // 출퇴근_일일 list-ByDeptKey : 부서키 기준
    List<OfficeCommuteDayVO> selectCommuteListByDeptKey(OfficeCommuteDayVO vo);

    // 출퇴근_일일 list-ByDeptKey totalCount : 부서키 기준
    int selectCommuteListTotalCountByDeptKey(OfficeCommuteDayVO vo);

    // 춭/퇴근 대상 직원 list
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

    // 출퇴근 목록 : 공통(total count)
    int selectCommuteListTotalCount(OfficeCommuteDayVO vo);

    // {일} 팀원 출퇴근 현황 : 팀장
    List<StatsCommonVO> selectCommuteStatsDayTypeManager(OfficeCommuteDayVO vo);

    // 부서 출퇴근 반영시 일일_출퇴근 테이블 제출여부 반영완료
    int completeDeptSubmit(OfficeCommuteDayVO vo);

    // {월, 기간} 팀원 출퇴근 현황 : 팀장
    List<OfficeCommuteDeyManagerStatsVO> selectCommuteStatsMonthTypeManager(OfficeCommuteDayVO vo);

    // {월, 기간} 팀원 출퇴근 현황 1 : 실장
    List<StatsCommonVO> selectCommuteSimpleStatsMonthTypeHeader(OfficeCommuteDayVO vo);

    // {월, 기간} 팀원 출퇴근 현황 2 : 실장
    List<OfficeCommuteDeyHeaderStatsVO> selectCommuteStatsMonthTypeHeader(OfficeCommuteDayVO vo);

    // 전체 현황 : 하루, 기간
    List<StatsCommonVO> selectCommuteStatsDayTypeAdmin(OfficeCommuteDayVO vo);

}
