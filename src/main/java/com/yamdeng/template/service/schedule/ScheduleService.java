package com.yamdeng.template.service.schedule;

import com.yamdeng.template.constant.Constant;
import com.yamdeng.template.data.dao.*;
import com.yamdeng.template.service.AlarmMessageService;
import com.yamdeng.template.vo.common.BaseCommonVO;
import com.yamdeng.template.vo.db.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ScheduleService {

    @Autowired
    private CommuteDao commuteDao;

    @Autowired
    private VacationDao vacationDao;

    @Autowired
    private HolidayDao holidayDao;

    @Autowired
    private PushAlarmDao pushAlarmDao;

    @Autowired
    private DeptWorkTimeSettingDao deptWorkTimeSettingDao;

    @Autowired
    private UserDao userDao;

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
            OfficeCommuteDayVO alreadyCheckCommuteDayInfo = commuteDao.selectCommuteInfo(
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

    public void alarmCommuteDayByPrivate() {

        // 공휴일 체크 : 해당일이 공휴일인지 확인
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = LocalDate.now();
        int weekValue = now.getDayOfWeek().getValue();
        String nowBaseDateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String nowBaseYear = now.format(DateTimeFormatter.ofPattern("yyyy"));
        String currentTimeStr = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime currentTime = LocalTime.parse(currentTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime tardyCheckTime = currentTime.plusMinutes(0);

        // 토요일, 일요일이 아닌 경우만 처리
        if(weekValue != 6 && weekValue != 7) {

            int isHoliday = holidayDao.checkHolidayByDateStr(
                    OfficeWorkHolidayVO.builder()
                            .baseYear(nowBaseYear)
                            .holiDateStr(nowBaseDateStr)
                            .build());

            // 공휴일이 아닌 경우만 처리
            if(isHoliday < 1) {

                // 출퇴근 대상 목록 전체가져옴
                List<BaseCommonVO> userList = commuteDao.selectCommuteTargetUserList();

                // 출퇴근 대상 목록 start
                userList.stream().forEach(userInfo -> {
                   String userKey = userInfo.getUserKey();
                   String deptKey = userInfo.getDeptKey();
                    // 이미 알림 테이블에 저장했는지 확인 : 개인출퇴근
                    OfficePushAlarmVO checkAlreadySearchVo =
                            OfficePushAlarmVO.builder()
                                    .baseDateStr(nowBaseDateStr)
                                    .userId(userKey)
                                    .alarmKindCode(Constant.CODE_ALARM_COMMUTE_PRIVATE)
                                    .build();
                    int isAlreadyAlarm = pushAlarmDao.checkAlreadySave(checkAlreadySearchVo);

                    // 알림을 이미 보냈으면 처리를 하지 않음
                    if(isAlreadyAlarm < 1) {
                        OfficeCommuteDayVO officeCommuteDayVO = commuteDao.selectCommuteInfo(
                                OfficeCommuteDayVO.builder()
                                        .baseDateStr(nowBaseDateStr)
                                        .userId(userKey)
                                        .build()
                        );
                        // 일일 출퇴근 정보가 존재하고 && 출근을 하지 않았을 경우만 처리
                        if(officeCommuteDayVO != null && officeCommuteDayVO.getStartWorkDate() != null) {
                            OfficeVacationDetailDayHistoryVO officeVacationDetailDayHistoryVO =
                                    vacationDao.selectVacationDetailDayHistoryInfo(
                                            OfficeVacationDetailDayHistoryVO.builder()
                                                    .baseDateStr(nowBaseDateStr)
                                                    .userId(userKey)
                                                    .build()
                                    );
                            boolean isAlarmCheck = false;
                            if(officeVacationDetailDayHistoryVO != null) {
                                String vacationCode = officeVacationDetailDayHistoryVO.getVacationKindCode();
                                // 휴가정보가 존재할 경우 알림 체크를 하지 않음 : 오전반차인 경우만 체크 나머지는 체크하지 않음
                                if(Constant.CODE_VACATION_KIND_VACATION_MORNING.equals(vacationCode)) {
                                    isAlarmCheck = true;
                                }
                            } {
                                isAlarmCheck = true;
                            }
                            if(isAlarmCheck) {

                                // 부서의 설정시간을 가져오고 존재하지 않을 경우는 기본 정책을 사용해야 함
                                OfficeDeptWorkTimeSettingVO officeDeptWorkTimeSettingVO =
                                        deptWorkTimeSettingDao.selectDeptWorkTimeSettingInfoByDeptId(deptKey);
                                String workStartTime = Constant.POLICY_DEFAULT_WORK_START_TIME;
                                String lunchStartTime = Constant.POLICY_DEFAULT_LUNCH_START_TIME;
                                String lunchEndTime = Constant.POLICY_DEFAULT_LUNCH_END_TIME;

                                if(officeDeptWorkTimeSettingVO != null) {
                                    String applyStartDateStr = officeDeptWorkTimeSettingVO.getApplyStartDateStr();
                                    String applyEndDateStr = officeDeptWorkTimeSettingVO.getApplyEndDateStr();
                                    // 오늘날짜 기준으로 적용 대상인지 확인
                                    DateTimeFormatter startFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                                    LocalDate applyStartDate = LocalDate.parse(applyStartDateStr, startFormatter);
                                    Period startDiffPeriod = Period.between(applyStartDate, nowDate);
                                    DateTimeFormatter endFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                                    LocalDate applyEndDate = null;
                                    Period endDiffPeriod = null;
                                    int startDiffDays = startDiffPeriod.getDays();
                                    int endDiffDays = 0;
                                    if(StringUtils.hasText(applyEndDateStr)) {
                                        applyEndDate = LocalDate.parse(applyEndDateStr, endFormatter);
                                        endDiffPeriod = Period.between(applyEndDate, nowDate);
                                        endDiffDays = endDiffPeriod.getDays();
                                    }

                                    // 현재일이 적용시작일보다 같거나 큰 경우만 해당
                                    // 적용종료일이 null 이면 반영
                                    // 적용종료일이 존재하고 현재일보다 작은 경우만 해당
                                    if(startDiffDays >= 0 && endDiffDays <= 0) {
                                        workStartTime = officeDeptWorkTimeSettingVO.getWorkStartTime();
                                        lunchStartTime = officeDeptWorkTimeSettingVO.getLunchStartTime();
                                        lunchEndTime = officeDeptWorkTimeSettingVO.getLunchEndTime();
                                    }

                                }

                                // 오전 반차인 경우는 오후 출근시간을 가져와서 해당 시간보다 지나도 출근을 하지 않았을 경우 반영
                                // 오전반차가 아닌 경우는 순수 출근 시간으로 체크
                                if(officeVacationDetailDayHistoryVO != null) {
                                    String vacationCode = officeVacationDetailDayHistoryVO.getVacationKindCode();
                                    if(Constant.CODE_VACATION_KIND_VACATION_MORNING.equals(vacationCode)) {

                                        double startWorkTimeNumber = Double.parseDouble(workStartTime.substring(0, 2));
                                        double startWorkFixedValue = "3".equals(workStartTime.substring(3, 4)) ? .5 : 0;
                                        double startLunchTimeNumber = Double.parseDouble(lunchStartTime.substring(0, 2));
                                        double startLunchFixedValue = "3".equals(lunchStartTime.substring(3, 4)) ? .5 : 0;
                                        double endLunchTimeNumber = Double.parseDouble(lunchEndTime.substring(0, 2));
                                        double endLunchFixedValue = "3".equals(lunchEndTime.substring(3, 4)) ? .5 : 0;

                                        startWorkTimeNumber = startWorkTimeNumber + startWorkFixedValue;
                                        startLunchTimeNumber = startLunchTimeNumber + startLunchFixedValue;
                                        endLunchTimeNumber = endLunchTimeNumber + endLunchFixedValue;
                                        double calculateValue = 4 - (startLunchTimeNumber - startWorkTimeNumber);
                                        double afterTardyTimeNumberValue = endLunchTimeNumber + calculateValue;
                                        log.info("startWorkTimeNumber : {}", startWorkTimeNumber);
                                        log.info("starLunchTimeNumber : {}", startLunchTimeNumber);
                                        log.info("endLunchTimeNumber : {}", endLunchTimeNumber);
                                        log.info("calculateValue : {}", calculateValue);
                                        log.info("afterTardyTimeNumberValue : {}", afterTardyTimeNumberValue);
                                        String afterTardyTimeStr = Double.toString(afterTardyTimeNumberValue);
                                        log.info("afterTardyTimeStr : {}", afterTardyTimeStr);
                                        String afterTardyTime = afterTardyTimeStr.substring(0, 2) + ":";
                                        if("5".equals(afterTardyTimeStr.substring(3, 4))) {
                                            afterTardyTime = afterTardyTime + "30";
                                        } else {
                                            afterTardyTime = afterTardyTime + "00";
                                        }
                                        workStartTime = afterTardyTime;
                                    }
                                }

                                // 현재시간 기준으로 설정된 시간보다 지각을 하였는지 확인하기
                                long minutesBetween = ChronoUnit.MINUTES.between(
                                        LocalTime.parse(workStartTime, DateTimeFormatter.ofPattern("HH:mm"))
                                        ,tardyCheckTime);
                                log.info("minutesBetween : {}", minutesBetween);
                                if(minutesBetween > 0) {
                                    // 1.지각알림처리 : table insert
                                    Object[] messageArguments = { nowBaseDateStr };
                                    String alarmTitle = AlarmMessageService.getMessage(Constant.CODE_ALARM_COMMUTE_PRIVATE, messageArguments);
                                    OfficePushAlarmVO insertOfficePushAlarmVO =
                                            OfficePushAlarmVO.builder()
                                                    .baseDateStr(nowBaseDateStr)
                                                    .userId(userKey)
                                                    .alarmKindCode(Constant.CODE_ALARM_COMMUTE_PRIVATE)
                                                    .alarmTitle(alarmTitle)
                                                    .alarmContent(alarmTitle)
                                                    .build();
                                    pushAlarmDao.insertPushAlarm(insertOfficePushAlarmVO);
                                }

                            }
                        }
                    }
                });
                // 출퇴근 대상 목록 end

            }
        }
    }

    public void alarmCommuteDayByManager() {

        // 공휴일 체크 : 해당일이 공휴일인지 확인
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = LocalDate.now();
        int weekValue = now.getDayOfWeek().getValue();
        String nowBaseDateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String nowBaseYear = now.format(DateTimeFormatter.ofPattern("yyyy"));
        String currentTimeStr = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime currentTime = LocalTime.parse(currentTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime tardyCheckTime = currentTime.plusMinutes(10);

        // 토요일, 일요일이 아닌 경우만 처리
        if(weekValue != 6 && weekValue != 7) {

            int isHoliday = holidayDao.checkHolidayByDateStr(
                    OfficeWorkHolidayVO.builder()
                            .baseYear(nowBaseYear)
                            .holiDateStr(nowBaseDateStr)
                            .build());

            // 공휴일이 아닌 경우만 처리
            if(isHoliday < 1) {

                // 출퇴근 대상 목록 전체가져옴
                List<BaseCommonVO> userList = commuteDao.selectCommuteTargetUserList();

                // 출퇴근 대상 목록 start
                userList.stream().forEach(userInfo -> {
                    String userKey = userInfo.getUserKey();
                    String deptKey = userInfo.getDeptKey();
                    String userName = userInfo.getUserName();
                    String managerKey = userDao.getDeptManagerUserId(deptKey);
                    // 이미 알림 테이블에 저장했는지 확인 : 개인출퇴근
                    OfficePushAlarmVO checkAlreadySearchVo =
                            OfficePushAlarmVO.builder()
                                    .baseDateStr(nowBaseDateStr)
                                    .userId(managerKey)
                                    .targetId(userKey)
                                    .alarmKindCode(Constant.CODE_ALARM_COMMUTE_MANAGER)
                                    .build();
                    int isAlreadyAlarm = pushAlarmDao.checkAlreadySave(checkAlreadySearchVo);

                    // 알림을 이미 보냈으면 처리를 하지 않음
                    if(isAlreadyAlarm < 1) {
                        OfficeCommuteDayVO officeCommuteDayVO = commuteDao.selectCommuteInfo(
                                OfficeCommuteDayVO.builder()
                                        .baseDateStr(nowBaseDateStr)
                                        .userId(userKey)
                                        .build()
                        );
                        // 일일 출퇴근 정보가 존재하고 && 출근을 하지 않았을 경우만 처리
                        if(officeCommuteDayVO != null && officeCommuteDayVO.getStartWorkDate() != null) {
                            OfficeVacationDetailDayHistoryVO officeVacationDetailDayHistoryVO =
                                    vacationDao.selectVacationDetailDayHistoryInfo(
                                            OfficeVacationDetailDayHistoryVO.builder()
                                                    .baseDateStr(nowBaseDateStr)
                                                    .userId(userKey)
                                                    .build()
                                    );
                            boolean isAlarmCheck = false;
                            if(officeVacationDetailDayHistoryVO != null) {
                                String vacationCode = officeVacationDetailDayHistoryVO.getVacationKindCode();
                                // 휴가정보가 존재할 경우 알림 체크를 하지 않음 : 오전반차인 경우만 체크 나머지는 체크하지 않음
                                if(Constant.CODE_VACATION_KIND_VACATION_MORNING.equals(vacationCode)) {
                                    isAlarmCheck = true;
                                }
                            } {
                                isAlarmCheck = true;
                            }
                            if(isAlarmCheck) {

                                // 부서의 설정시간을 가져오고 존재하지 않을 경우는 기본 정책을 사용해야 함
                                OfficeDeptWorkTimeSettingVO officeDeptWorkTimeSettingVO =
                                        deptWorkTimeSettingDao.selectDeptWorkTimeSettingInfoByDeptId(deptKey);
                                String workStartTime = Constant.POLICY_DEFAULT_WORK_START_TIME;
                                String lunchStartTime = Constant.POLICY_DEFAULT_LUNCH_START_TIME;
                                String lunchEndTime = Constant.POLICY_DEFAULT_LUNCH_END_TIME;

                                if(officeDeptWorkTimeSettingVO != null) {
                                    String applyStartDateStr = officeDeptWorkTimeSettingVO.getApplyStartDateStr();
                                    String applyEndDateStr = officeDeptWorkTimeSettingVO.getApplyEndDateStr();
                                    // 오늘날짜 기준으로 적용 대상인지 확인
                                    DateTimeFormatter startFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                                    LocalDate applyStartDate = LocalDate.parse(applyStartDateStr, startFormatter);
                                    Period startDiffPeriod = Period.between(applyStartDate, nowDate);
                                    DateTimeFormatter endFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                                    LocalDate applyEndDate = null;
                                    Period endDiffPeriod = null;
                                    int startDiffDays = startDiffPeriod.getDays();
                                    int endDiffDays = 0;
                                    if(StringUtils.hasText(applyEndDateStr)) {
                                        applyEndDate = LocalDate.parse(applyEndDateStr, endFormatter);
                                        endDiffPeriod = Period.between(applyEndDate, nowDate);
                                        endDiffDays = endDiffPeriod.getDays();
                                    }

                                    // 현재일이 적용시작일보다 같거나 큰 경우만 해당
                                    // 적용종료일이 null 이면 반영
                                    // 적용종료일이 존재하고 현재일보다 작은 경우만 해당
                                    if(startDiffDays >= 0 && endDiffDays <= 0) {
                                        workStartTime = officeDeptWorkTimeSettingVO.getWorkStartTime();
                                        lunchStartTime = officeDeptWorkTimeSettingVO.getLunchStartTime();
                                        lunchEndTime = officeDeptWorkTimeSettingVO.getLunchEndTime();
                                    }

                                }

                                // 오전 반차인 경우는 오후 출근시간을 가져와서 해당 시간보다 지나도 출근을 하지 않았을 경우 반영
                                // 오전반차가 아닌 경우는 순수 출근 시간으로 체크
                                if(officeVacationDetailDayHistoryVO != null) {
                                    String vacationCode = officeVacationDetailDayHistoryVO.getVacationKindCode();
                                    if(Constant.CODE_VACATION_KIND_VACATION_MORNING.equals(vacationCode)) {

                                        double startWorkTimeNumber = Double.parseDouble(workStartTime.substring(0, 2));
                                        double startWorkFixedValue = "3".equals(workStartTime.substring(3, 4)) ? .5 : 0;
                                        double startLunchTimeNumber = Double.parseDouble(lunchStartTime.substring(0, 2));
                                        double startLunchFixedValue = "3".equals(lunchStartTime.substring(3, 4)) ? .5 : 0;
                                        double endLunchTimeNumber = Double.parseDouble(lunchEndTime.substring(0, 2));
                                        double endLunchFixedValue = "3".equals(lunchEndTime.substring(3, 4)) ? .5 : 0;

                                        startWorkTimeNumber = startWorkTimeNumber + startWorkFixedValue;
                                        startLunchTimeNumber = startLunchTimeNumber + startLunchFixedValue;
                                        endLunchTimeNumber = endLunchTimeNumber + endLunchFixedValue;
                                        double calculateValue = 4 - (startLunchTimeNumber - startWorkTimeNumber);
                                        double afterTardyTimeNumberValue = endLunchTimeNumber + calculateValue;
                                        log.info("startWorkTimeNumber : {}", startWorkTimeNumber);
                                        log.info("starLunchTimeNumber : {}", startLunchTimeNumber);
                                        log.info("endLunchTimeNumber : {}", endLunchTimeNumber);
                                        log.info("calculateValue : {}", calculateValue);
                                        log.info("afterTardyTimeNumberValue : {}", afterTardyTimeNumberValue);
                                        String afterTardyTimeStr = Double.toString(afterTardyTimeNumberValue);
                                        log.info("afterTardyTimeStr : {}", afterTardyTimeStr);
                                        String afterTardyTime = afterTardyTimeStr.substring(0, 2) + ":";
                                        if("5".equals(afterTardyTimeStr.substring(3, 4))) {
                                            afterTardyTime = afterTardyTime + "30";
                                        } else {
                                            afterTardyTime = afterTardyTime + "00";
                                        }
                                        workStartTime = afterTardyTime;
                                    }
                                }

                                // 현재시간 기준으로 설정된 시간보다 지각을 하였는지 확인하기
                                long minutesBetween = ChronoUnit.MINUTES.between(
                                        LocalTime.parse(workStartTime, DateTimeFormatter.ofPattern("HH:mm"))
                                        ,tardyCheckTime);
                                log.info("minutesBetween : {}", minutesBetween);
                                if(minutesBetween > 0) {
                                    // 1.지각알림처리 : table insert
                                    Object[] messageArguments = { userName };
                                    String alarmTitle = AlarmMessageService.getMessage(Constant.CODE_ALARM_COMMUTE_MANAGER, messageArguments);
                                    OfficePushAlarmVO insertOfficePushAlarmVO =
                                            OfficePushAlarmVO.builder()
                                                    .baseDateStr(nowBaseDateStr)
                                                    .userId(managerKey)
                                                    .targetId(userKey)
                                                    .alarmKindCode(Constant.CODE_ALARM_COMMUTE_MANAGER)
                                                    .alarmTitle(alarmTitle)
                                                    .alarmContent(alarmTitle)
                                                    .build();
                                    pushAlarmDao.insertPushAlarm(insertOfficePushAlarmVO);

                                    // 2.일일출퇴근 테이블에 근무결과 지각으로 반영
                                    OfficeCommuteDayVO vo =
                                            OfficeCommuteDayVO.builder()
                                                    .baseDateStr(nowBaseDateStr)
                                                    .userId(userKey)
                                                    .workResultCode(Constant.CODE_WORK_RESULT_TARDY)
                                                    .modUserId(Constant.POLICY_SYSTEM_USER_ID)
                                                    .tardyYn("Y")
                                                    .build();
                                    commuteDao.updateCommute(vo);
                                }

                            }
                        }
                    }
                });
                // 출퇴근 대상 목록 end

            }
        }

    }

}
