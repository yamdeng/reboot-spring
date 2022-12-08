package com.yamdeng.template.util;

import com.yamdeng.template.vo.common.MonthWeekPeriodVO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GWDateUtil {

    // 시작일,종료일 기준으로 전체 날짜 문자열 반환 : yyyyMMdd 포맷
    public static List<String> getDateListStringByTwoDate(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        List<String> result = new ArrayList<>();
        long diffDays = ChronoUnit.DAYS.between(startDate, endDate);
        if(diffDays >= 0) {
            int plusDay = 0;
            while(true) {
                LocalDate savedDate = startDate.plusDays(plusDay);
                if(ChronoUnit.DAYS.between(savedDate, endDate) < 0) {
                    break;
                }
                String plusDateStr = savedDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                result.add(plusDateStr);
                plusDay++;
            }
        }
        return result;
    }

    // 월 기준으로 1 ~ 6주 별로 각각 시작기간 ~ 종료기간 정보 가져오기
    public static List<MonthWeekPeriodVO> getMonthWeekPeriodListByMonth(String month) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse(month + "01", formatter);
        YearMonth monthDate = YearMonth.from(startDate);
        LocalDate lastDate = monthDate.atEndOfMonth();
        int firstDateDayOfWeek = startDate.getDayOfWeek().getValue();
        int firstSundayPeriodValue = 7 - firstDateDayOfWeek;
        LocalDate nextSundayDate = startDate.plusDays(firstSundayPeriodValue);
        int applyWeek = 1;
        List<MonthWeekPeriodVO> result = new ArrayList<>();

        while(true) {
            long diffDays = ChronoUnit.DAYS.between(nextSundayDate, lastDate);
            if(diffDays <= 0) {
                nextSundayDate = lastDate;
            }
            String startDateStr = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String endDateStr = nextSundayDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            MonthWeekPeriodVO resultVO = MonthWeekPeriodVO.builder()
                    .week(applyWeek)
                    .startDateStr(startDateStr)
                    .endDateStr(endDateStr)
                    .build();
            resultVO.setDateStringList(GWDateUtil.getDateListStringByTwoDate(startDateStr, endDateStr));
            result.add(resultVO);

            startDate = nextSundayDate.plusDays(1);
            nextSundayDate = nextSundayDate.plusDays(7);
            applyWeek++;
            if(applyWeek > 6 || diffDays <= 0) {
                break;
            }
        }

        return result;
    }

}
