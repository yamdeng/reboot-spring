package com.yamdeng.template.service.schedule;

import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.CommuteDao;
import com.yamdeng.template.data.dao.VacationDao;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.db.OfficeVacationDetailDayHistoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ScheduleService {

    @Autowired
    private CommuteDao commuteDao;

    @Autowired
    private VacationDao vacationDao;

    public void createDayCommute(final String baseDateStr) {

        /*

           1.출퇴근 대상이 되는 사용자 목록을 가져온다
           2.1번에서 가져온 사용자 목록을 반복한다
            2-1.기준날짜의 일별 휴가 상세 이력을 가져온다
            2-1-1.휴가승인 이력이 존재하면 근무상태, 근무결과, 휴가코드를 출퇴근 일일정보 테이블에 추가한다
            2-1-2.휴가승인 이력이 존재하지 않는 경우 근무상태, 근무결과, 휴가코드 값을 null을 기준으로 출퇴근 일일정보 테이블에 추가한다

         */

        List<BaseCommonVO> userList = commuteDao.selectCommuteTargetUserList();
        userList.stream().forEach(userInfo -> {
            String userKey = userInfo.getUserKey();
            OfficeVacationDetailDayHistoryVO officeVacationDetailDayHistoryVO =
                    vacationDao.selectVacationDetailDayHistoryInfo(
                            OfficeVacationDetailDayHistoryVO.builder()
                                    .baseDateStr(baseDateStr)
                                    .userId(userKey)
                                    .build()
                    );
            // 이미 추가한 출퇴근 일일 정보가 존재하는지 체크
            OfficeCommuteDayVO alreadyCheckCommuteDayInfo = commuteDao.selectCommuteInfoByUserId(
                    OfficeCommuteDayVO.builder().baseDateStr(baseDateStr).userId(userKey).build()
            );
            if(alreadyCheckCommuteDayInfo == null) {
                // 휴가 이력이 존재할 경우 해당 경우를 근무상태, 근무결과에 반영
                if(officeVacationDetailDayHistoryVO != null) {
                    String vacationCode = officeVacationDetailDayHistoryVO.getVacationKindCode();
                    String workStatusCode = vacationCode;
                    String workResultCode = vacationCode;
                    // 오후반차인 경우는 null로 처리
                    if(vacationCode.equals(Constant.CODE_VACATION_KIND_VACATION_AFTERNOON)) {
                        workResultCode = null;
                    } else if(vacationCode.equals(Constant.CODE_VACATION_KIND_VACATION_MORNING)) {
                        // 오전 반차인 경우는 정상출근(오전반차)로 반영
                        workResultCode = Constant.CODE_WORK_RESULT_SUCCESS_MORNING;
                    }
                    OfficeCommuteDayVO insertCommuteDayVO =
                            OfficeCommuteDayVO.builder()
                                    .baseDateStr(baseDateStr)
                                    .userId(userKey)
                                    .workStatusCode(workStatusCode)
                                    .workResultCode(workResultCode)
                                    .vacationKindCode(vacationCode)
                                    .workedTimeValue(0.0)
                                    .build();
                    commuteDao.insertCommute(insertCommuteDayVO);
                } else {
                    // 휴가이력이 존재하지 않을 경우 처리
                    OfficeCommuteDayVO insertCommuteDayVO =
                            OfficeCommuteDayVO.builder()
                                    .baseDateStr(baseDateStr)
                                    .userId(userKey)
                                    .workStatusCode(null)
                                    .workResultCode(null)
                                    .vacationKindCode(null)
                                    .workedTimeValue(0.0)
                                    .build();
                    commuteDao.insertCommute(insertCommuteDayVO);
                }
            }
        });

    }

}
