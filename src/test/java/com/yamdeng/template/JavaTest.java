package com.yamdeng.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
public class JavaTest {

    @Test
    void testWorkedTimeValue() {
        double result = (double)73 / (double)60;
        log.info("result : {}", Math.ceil(result * 10) / 10.0);
    }

    @Test
    void testNowBaseDateStr() {
        LocalDateTime now = LocalDateTime.now();
        String nowBaseDateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("testNowBaseDateStr : {}", nowBaseDateStr);
    }

    @Test
    void testNowBaseYear() {
        LocalDateTime now = LocalDateTime.now();
        String nowBaseYear = now.format(DateTimeFormatter.ofPattern("yyyy"));
        log.info("testNowBaseYear : {}", nowBaseYear);
    }

    @Test
    void testYesterdayBaseDateStr() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        String yesterdayBaseYear = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("testYesterdayBaseDateStr : {}", yesterdayBaseYear);
    }

    @Test
    void testBeforeYear() {
        LocalDate today = LocalDate.now();
        LocalDate beforeYear = today.minusYears(1);
        String beforeYearStr = beforeYear.format(DateTimeFormatter.ofPattern("yyyy"));
        log.info("testBeforeYear : {}", beforeYearStr);
    }

    @Test
    void testNowHHMM() {
        LocalDateTime now = LocalDateTime.now();
        String nowBaseYear = now.format(DateTimeFormatter.ofPattern("hh"));
        log.info("testNowBaseYear : {}", nowBaseYear);
    }

    @Test
    void testAfterHalfTime() {

        // 1.업무 시작시간을 가져옴
        // 2.점심 시작시간을 가져옴
        // 3.점심 종료시간을 가져옴
        // 4.점심 시작시간 - 업무시작시간을 숫자로 반환
        // 5.4 - 4번결과값
        // 6.점심 종료시간 + 5번 결과값을 시간으로 변환
        String startWorkTime = "09:30"; // 09:00, 09:30
        String startLunchTime = "12:00";
        String endLunchTime = "13:00";
        double startWorkTimeNumber = Double.parseDouble(startWorkTime.substring(0, 2));
        double startWorkFixedValue = "3".equals(startWorkTime.substring(3, 4)) ? .5 : 0;

        double startLunchTimeNumber = Double.parseDouble(startLunchTime.substring(0, 2));
        double startLunchFixedValue = "3".equals(startLunchTime.substring(3, 4)) ? .5 : 0;

        double endLunchTimeNumber = Double.parseDouble(endLunchTime.substring(0, 2));
        double endLunchFixedValue = "3".equals(endLunchTime.substring(3, 4)) ? .5 : 0;

        startWorkTimeNumber = startWorkTimeNumber + startWorkFixedValue;
        startLunchTimeNumber = startLunchTimeNumber + startLunchFixedValue;
        endLunchTimeNumber = endLunchTimeNumber + endLunchFixedValue;
        double calculateValue = 4 - (startLunchTimeNumber - startWorkTimeNumber);
        double resultNumberValue = endLunchTimeNumber + calculateValue;
        log.info("startWorkTimeNumber : {}", startWorkTimeNumber);
        log.info("starLunchTimeNumber : {}", startLunchTimeNumber);
        log.info("endLunchTimeNumber : {}", endLunchTimeNumber);
        log.info("calculateValue : {}", calculateValue);
        log.info("resultNumberValue : {}", resultNumberValue);
        String result = Double.toString(resultNumberValue);
        log.info("result : {}", result);
        String finalResult = result.substring(0, 2) + ":";
        if("5".equals(result.substring(3, 4))) {
            finalResult = finalResult + "30";
        } else {
            finalResult = finalResult + "00";
        }
        log.info("finalResult : {}", finalResult);
    }

    @Test
    void testHolidayCheck() {
        LocalDateTime now = LocalDateTime.now();
        int weekValue = now.getDayOfWeek().getValue();
        log.info("testHolidayCheck : {}", weekValue);
    }

    @Test
    void testLocaDateDiff() {
        // 오늘 날짜를 가져옴
        LocalDate now = LocalDate.now();
        String applyStartDateStr = "20221205";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate applyStartDate = LocalDate.parse(applyStartDateStr, formatter);
        Period startDiffPeriod = Period.between(applyStartDate, now);
        int startDiffDays = startDiffPeriod.getDays();
        log.info("startDiffDays : {}", startDiffDays);
        // 현재일이 시작일보다 같거나 큰 경우만 해당
        if(startDiffDays >= 0) {
            log.info("start success!!!!!!!!!!!!");
        } else {
            log.info("start fail!!!!!!!!!!!!");
        }

        String applyEndDateStr = "20221203";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate applyEndDate = LocalDate.parse(applyEndDateStr, formatter2);
        Period endDiffPeriod = Period.between(applyEndDate, now);
        int endDiffDays = endDiffPeriod.getDays();
        log.info("endDiffDays : {}", endDiffDays);
        // 종료일이 존재하고 현재일보다 같거나 작은 경우만 해당
        if(endDiffDays <= 0) {
            log.info("end success!!!!!!!!!!!!");
        } else {
            log.info("end fail!!!!!!!!!!!!");
        }

    }

    @Test
    void testHHMM() {
        LocalDateTime dateTime = LocalDateTime.parse(
        "2016-10-31 07:59:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        String dateStr = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        log.info("dateStr : {}", dateStr);

        LocalTime localTime = LocalTime.parse(dateStr, DateTimeFormatter.ofPattern("HH:mm"));
        log.info("localTime : {}", localTime);
    }

    @Test
    void testDiffTime() {
        LocalTime localTime1 = LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"));
        localTime1 = localTime1.plusMinutes(5);

        LocalTime localTime2 = LocalTime.parse("09:10", DateTimeFormatter.ofPattern("HH:mm"));
        long minutesBetween = ChronoUnit.MINUTES.between(localTime1,localTime2);
        log.info("localTime : {}", minutesBetween);
    }

    @Test
    void testUUID() {
        String result = UUID.randomUUID().toString();
        String result2 = UUID.randomUUID().toString();
        log.info("uuid string : {}", result);
        log.info("uuid string2 : {}", result2);
    }

    @Test
    void testRecent7Days() {
        LocalDate today = LocalDate.now();
        LocalDate before6Day = today.minusDays(6);
        String before6DayStr = before6Day.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("before6DayStr : {}", before6DayStr);
    }

    // 오늘 기준으로 이전 평일 구하기
    @Test
    void beforeWorkDay() {

        LocalDate today = LocalDate.now();
        int minusDay = 1;
        while(true) {
            LocalDate beforeDate = today.minusDays(minusDay);
            int weekValue = beforeDate.getDayOfWeek().getValue();
            // 토요일, 일요일 아니고
            if(weekValue != 6 && weekValue != 7) {
                // 공휴일 테이블 확인
            }
        }

    }

}
