package com.yamdeng.template.service.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ScheduleConfigService {

    private final ScheduleService scheduleService;

    // 출퇴근_일일_정보 생성 ===> 01:00 실행 (매일)
    @Scheduled(cron = "0 0 1 * * *")
    public void createDayCommute () {
        LocalDateTime now = LocalDateTime.now();
        String baseDateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("createDayCommute 시작 : {}", baseDateStr);
        scheduleService.createDayCommute(baseDateStr);
        log.info("createDayCommute 종료 : {}", baseDateStr);
    }

    // 일일출퇴근(개인) 출퇴근 액션 가이드 알림 : 지정된 출근 시간이 지난 경우 ===> 07:00부터 17:00 까지 10분마다(공휴일 제외)
    @Scheduled(cron = "0 0/10 7-17 * * *")
    public void alarmCommuteDayByPrivate () {
        log.info("alarmCommuteDayByPrivate 시작");
        scheduleService.alarmCommuteDayByPrivate();
        log.info("alarmCommuteDayByPrivate 종료");
    }

    // 일일출퇴근(팀장) 팀원 지각 알림(지각도 같이 처리됨) : 지정된 출근 시간이 지난 경우 ===> 07:00부터 17:00 까지 10분마다(공휴일 제외)
    @Scheduled(cron = "0 0/10 7-17 * * *")
    public void alarmCommuteDayByManager () {
        log.info("alarmCommuteDayByManager 시작");
        scheduleService.alarmCommuteDayByManager();
        log.info("alarmCommuteDayByManager 종료");
    }

    // 출퇴근 보고 : 지정된 출근 시간이 지난 경우 ===> 07:00부터 17:00 까지 10분마다(팀원, 팀장 같이 반영) (공휴일 제외)
    @Scheduled(cron = "0 0/10 7-17 * * *")
    public void alarmCommuteDay () {

//        #2 팀 출퇴근 제출 기한이 지난 경우
//> {일자} {부서명} 출퇴근 기록을 제출해주세요.(Link)

        /*

            1.출퇴근 보고 목록중 제출이 않된 목록 조회
            2.추가로 팀장이 금일 휴가인지 확인하고(기간을 체크해야함) ===> 한번 보냈으면 보내지 않음

         */

        log.info("alarmCommuteDay");
    }

    // 출퇴근 보고 : 팀 출퇴근 제출 기한이 지난 경우 ===> 00:10, 12:10 두번 실행(공휴일 제외)
    @Scheduled(cron = "0 10 0,12 * * *")
    public void alarmDeptCommuteReport () {
        // 공휴일 체크
        // 오늘날짜 base_date_str
        // batch_execute_user_id
        log.info("alarmDeptCommuteReport");
    }

    // 업무 보고 : 팀 업무보고 등록 기간이 지난 경우 ===> 00:05, 12:05 두번 실행(공휴일 제외)
    @Scheduled(cron = "0 10 0,12 * * *")
    public void alarmWorkReport () {
        // 공휴일 체크
        // 오늘날짜 base_date_str
        // batch_execute_user_id
        log.info("alarmWorkReport");
    }

    // 통계_출퇴근_주간(어제) : 02:00 (매일)
    @Scheduled(cron = "0 0 2 * * *")
    public void createCommuteStatsWeek () {
        // 어제 날짜 기준으로 월요일 시작일 전달 : 월요일 ~ 일요일
        // batch_execute_user_id
        log.info("createCommuteStatsWeek");
    }

    // 통계_출퇴근_월간_주별(어제) : 02:20 (매일)
    @Scheduled(cron = "0 20 2 * * *")
    public void createCommuteStatsMonthWeek () {

//        COMMENT ON TABLE OFFICE_COMMUTE_MONTH_D_STATS IS '통계_출퇴근_월간_주별';
//
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.USER_ID is '직원ID';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.BASE_MONTH_STR is '기준년월';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.SUM_WORK_TIME_VALUE is '누적근무시간';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.FIRST_WEEK_TIME_VALUE is '1주 근무시간';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.SECOND_WEEK_TIME_VALUE is '2주 근무시간';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.THREE_WEEK_TIME_VALUE is '3주 근무시간';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.FOUR_WEEK_TIME_VALUE is '4주 근무시간';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.FIVE_WEEK_TIME_VALUE is '5주 근무시간';
//        comment on column OFFICE_COMMUTE_MONTH_D_STATS.SIX_WEEK_TIME_VALUE is '6주 근무시간';
        // 어제 날짜 기준으로 해당 월의 주단위 시간 가져오기
        // 1 ~ 6주 : 각 주별로 date 기간 가져오기
        log.info("createCommuteStatsMonthWeek");
    }

    // 통계_출퇴근_월간_휴일(어제) : 02:40 (매일)
    @Scheduled(cron = "0 40 2 * * *")
    public void createCommuteStatsMonthHoliday () {
        // 해당 월의 공휴일 목록 순서대로 가져오기
        log.info("createCommuteStatsMonthHoliday");
    }

    // 통계_휴가_월별_사용현황(어제 기준 년) : 03:00 (매일)
    @Scheduled(cron = "0 0 3 * * *")
    public void createVacationStatsMonth () {

//        COMMENT ON TABLE OFFICE_VACATION_MONTH_STATS IS '통계_휴가_월별_사용현황';
//
//        comment on column OFFICE_VACATION_MONTH_STATS.USER_ID is '직원ID';
//        comment on column OFFICE_VACATION_MONTH_STATS.BASE_YEAR is '기준년';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_1MONTH_COUNT is '1월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_2MONTH_COUNT is '2월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_3MONTH_COUNT is '3월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_4MONTH_COUNT is '4월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_5MONTH_COUNT is '5월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_6MONTH_COUNT is '6월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_7MONTH_COUNT is '7월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_8MONTH_COUNT is '8월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_9MONTH_COUNT is '9월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_10MONTH_COUNT is '10월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_11MONTH_COUNT is '11월 사용일수';
//        comment on column OFFICE_VACATION_MONTH_STATS.USE_12MONTH_COUNT is '12월 사용일수';

        // 해당 년의 각 사용일수 반영하기

        log.info("createVacationStatsMonth");
    }

    // 내년_휴가미리보기_생성 : 12월31일 04:00
    @Scheduled(cron = "0 0 4 31 12 *")
    public void createVacationYearPreview () {
        log.info("createVacationYearPreview");
    }

    // 공휴일 정보 동기화 ===> 03:00 실행 (매일)
    @Scheduled(cron = "0 0 3 * * *")
    public void syncHoliday () {
        // 오늘날짜 base_date_str
        // batch_execute_user_id
        log.info("createDayCommuteInfo");
    }

}
