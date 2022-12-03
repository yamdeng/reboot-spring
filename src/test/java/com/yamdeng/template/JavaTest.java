package com.yamdeng.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    void testAfterHalfTime() {

        // 1.업무 시작시간을 가져옴
        // 2.점심 시작시간을 가져옴
        // 3.점심 종료시간을 가져옴
        // 4.점심 시작시간 - 업무시작시간을 숫자로 반환
        // 5.4 - 4번결과값
        // 6.점심 종료시간 + 5번 결과값을 시간으로 변환
        String startWorkTime = "09:30"; // 09:00, 09:30
        String startLaunchTime = "12:00";
        String endLaunchTime = "13:00";
        double startWorkTimeNumber = Double.parseDouble(startWorkTime.substring(0, 2));
        double startWorkFixedValue = "3".equals(startWorkTime.substring(3, 4)) ? .5 : 0;

        double startLaunchTimeNumber = Double.parseDouble(startLaunchTime.substring(0, 2));
        double startLaunchFixedValue = "3".equals(startLaunchTime.substring(3, 4)) ? .5 : 0;

        double endLaunchTimeNumber = Double.parseDouble(endLaunchTime.substring(0, 2));
        double endLaunchFixedValue = "3".equals(endLaunchTime.substring(3, 4)) ? .5 : 0;

        startWorkTimeNumber = startWorkTimeNumber + startWorkFixedValue;
        startLaunchTimeNumber = startLaunchTimeNumber + startLaunchFixedValue;
        endLaunchTimeNumber = endLaunchTimeNumber + endLaunchFixedValue;
        double calculateValue = 4 - (startLaunchTimeNumber - startWorkTimeNumber);
        double resultNumberValue = endLaunchTimeNumber + calculateValue;
        log.info("startWorkTimeNumber : {}", startWorkTimeNumber);
        log.info("starLaunchTimeNumber : {}", startLaunchTimeNumber);
        log.info("endLaunchTimeNumber : {}", endLaunchTimeNumber);
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

}
