package com.yamdeng.template;

import com.yamdeng.template.util.GWDateUtil;
import com.yamdeng.template.vo.common.HolidayInfoVO;
import com.yamdeng.template.vo.common.MonthWeekPeriodVO;
import com.yamdeng.template.vo.db.OfficeCommuteDayVO;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthDStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteMonthHStatsVO;
import com.yamdeng.template.vo.stats.OfficeCommuteWeekStatsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class JavaTest {

    private static List<OfficeCommuteDayVO> commuteDayVOList = new ArrayList<>();


    static {

        Random random = new Random();
        List<String> dateStringList = GWDateUtil.getDateListStringByTwoDate("20221201", "20221218");
        List<String> userIdList = Arrays.asList("yamdeng1", "yamdeng2", "yamdeng3");
        userIdList.stream().forEach(userId -> {
            for (String dateStr : dateStringList) {
                commuteDayVOList.add(
                        OfficeCommuteDayVO.builder()
                                .userId(userId)
                                .baseDateStr(dateStr)
                                .workedTimeValue((double) random.nextInt(8))
                                .build()
                );
            }
        });

    }

    @Test
    void testWorkedTimeValue() {
        double result = (double)73 / (double)60;
        log.info("result : {}", Math.ceil(result * 10) / 10.0);
    }

    @Test
    void manualSetLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20220305", formatter);
        log.info("localDate : {}", localDate);
    }

    @Test
    void manualSetLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("20220305 07:59:59", formatter);
        log.info("localDateTime : {}", localDateTime);

        LocalDateTime localDateTime2 = LocalDateTime.parse(
                "2022-12-03 07:59:59",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        log.info("localDateTime2 : {}", localDateTime2);
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
    void testNowBaseMonth() {
        LocalDateTime now = LocalDateTime.now();
        String nowBaseMonth = now.format(DateTimeFormatter.ofPattern("yyyyMM"));
        String nowBaseOnlyMonth = now.format(DateTimeFormatter.ofPattern("MM"));
        log.info("nowBaseMonth : {}", nowBaseMonth);
        log.info("nowBaseOnlyMonth : {}", nowBaseOnlyMonth);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20221205", formatter); // 20220305, 20221205
        log.info("localDate.month : {}", localDate.format(DateTimeFormatter.ofPattern("MM")));
        log.info("localDate.month2 : {}", localDate.format(DateTimeFormatter.ofPattern("M")));

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
    void testBeforeMonth() {
//        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20220301", formatter);
        LocalDate beforeMonth = localDate.minusMonths(1);
        String beforeMonthStr = beforeMonth.format(DateTimeFormatter.ofPattern("MM"));
        log.info("beforeMonthStr : {}", beforeMonthStr);
        LocalDate afterMonth = localDate.plusMonths(1);
        String afterMonthStr = afterMonth.format(DateTimeFormatter.ofPattern("MM"));
        log.info("afterMonthStr : {}", afterMonthStr);
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
        String result = "";
        while(true) {
            LocalDate beforeDate = today.minusDays(minusDay);
            int weekValue = beforeDate.getDayOfWeek().getValue();
            // 토요일, 일요일 아니고
            if(weekValue != 6 && weekValue != 7) {
                // 공휴일 테이블 확인
                result = beforeDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            }
            minusDay++;
        }
        log.info("beforeWorkDay result : {}", result);
    }

    // 오늘 기준으로 이전평일의 이전일 구하기
    @Test
    void beforeWorkDayYesterday() {
        LocalDate today = LocalDate.now();
        int minusDay = 1;
        String beforeWorkDateStr = "";
        while(true) {
            LocalDate beforeDate = today.minusDays(minusDay);
            int weekValue = beforeDate.getDayOfWeek().getValue();
            // 토요일, 일요일 아니고
            if(weekValue != 6 && weekValue != 7) {
                // 공휴일 테이블 확인
                beforeWorkDateStr = beforeDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            }
            minusDay++;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(beforeWorkDateStr, formatter);
        LocalDate yesterday = localDate.minusDays(1);
        String result = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("beforeWorkDayYesterday result : {}", result);
    }

    // 오늘 기준으로 다음 평일 구하기
    @Test
    void afterWorkDay() {
        LocalDate today = LocalDate.now();
        int plusDay = 1;
        String result = "";
        while(true) {
            LocalDate afterDate = today.plusDays(plusDay);
            int weekValue = afterDate.getDayOfWeek().getValue();
            // 토요일, 일요일 아니고
            if(weekValue != 6 && weekValue != 7) {
                // 공휴일 테이블 확인
                result = afterDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            }
            plusDay++;
        }
        log.info("afterWorkDay result : {}", result);
    }

    // 오늘 기준으로 다음평일의 다음일 구하기
    @Test
    void afterWorkDayTomorrow() {
        LocalDate today = LocalDate.now();
        int plusDay = 1;
        String afterWorkDateStr = "";
        while(true) {
            LocalDate afterDate = today.minusDays(plusDay);
            int weekValue = afterDate.getDayOfWeek().getValue();
            // 토요일, 일요일 아니고
            if(weekValue != 6 && weekValue != 7) {
                // 공휴일 테이블 확인
                afterWorkDateStr = afterDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            }
            plusDay++;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(afterWorkDateStr, formatter);
        LocalDate tomorrow = localDate.minusDays(1);
        String result = tomorrow.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("afterWorkDayTomorrow result : {}", result);
    }

    // 특정일(월요일의 날짜) 기준으로 일요일날짜 구하기
    @Test
    void get7AfterDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20221205", formatter);
        String result = localDate.plusDays(6).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("get7AfterDate : {}", result);
    }

    // 특정일(월요일의 날짜) 기준으로 월~일요일까지 날짜 구하기
    @Test
    void get7Date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20221205", formatter);
        List<String> result = new ArrayList<>();
        for(int index = 0; index<7; index++) {
            String plusDateStr = localDate.plusDays(index).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            result.add(plusDateStr);
        }
        log.info("get7Date : {}", result);
    }

    // 일주일 기준으로 토요일인지, 일요일인지 체크하는 로직
    @Test
    void checkHolidayBy7Day() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("20221205", formatter);
        String after7Date = localDate.plusDays(6).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<String> sevenDateList = new ArrayList<>();
        for(int index = 0; index<7; index++) {
            String plusDateStr = localDate.plusDays(index).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            sevenDateList.add(plusDateStr);
        }

        // localDate, after7Date 기간으로 공휴일 테이블 조회
        List<String> holidayList = new ArrayList<>();

        List<HolidayInfoVO> result = new ArrayList<>();

        // sevenDateList 기준으로 공휴일(일요일 포함) 여부 체크해서 셋팅
        sevenDateList.stream().forEach(dateStr ->
            {
                LocalDate checkedDate = LocalDate.parse(dateStr, formatter);
                int weekValue = checkedDate.getDayOfWeek().getValue();
                boolean isHoliday = false;
                boolean isSaturday = false;
                long existCount = holidayList.stream().filter(holidayStr -> holidayStr.equals(dateStr)).count();
                if(existCount > 0) {
                    isHoliday = true;
                }
                if(!isHoliday) {
                    if(weekValue == 7) {
                        isHoliday = true;
                    } else if(weekValue == 6) {
                        isSaturday = true;
                    }
                }
                HolidayInfoVO holidayInfoVO = HolidayInfoVO.builder()
                        .isHoliday(isHoliday)
                        .isSaturday(isSaturday)
                        .dateStr(dateStr)
                        .build();
                result.add(holidayInfoVO);
            }
        );
        log.info("checkHolidayBy7Day : {}", result);
    }

    // 해당월의 1일, 마지막일 추출하기
    @Test
    void getFirstLastDateByMonth() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("202212" + "01", formatter);
        YearMonth monthDate = YearMonth.from(localDate);
        LocalDate firstDate = monthDate.atDay(1);
        LocalDate lastDate = monthDate.atEndOfMonth();
        String firstDateStr = firstDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String lastDateStr = lastDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("getFirstLastDateByMonth firstDateStr : {}", firstDateStr);
        log.info("getFirstLastDateByMonth lastDateStr : {}", lastDateStr);
    }

    // 월의 토요일/일요일/공휴일을 뽑아 오는 로직
    @Test
    void checkHolidayByMonth() {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("202212" + "01", formatter);
        YearMonth monthDate = YearMonth.from(localDate);
        LocalDate firstDate = monthDate.atDay(1);
        LocalDate lastDate = monthDate.atEndOfMonth();
        String firstDateStr = firstDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String lastDateStr = lastDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<String> monthDateList = new ArrayList<>();
        int plusIndex = 0;
        while(true) {
            String plusDateStr = firstDate.plusDays(plusIndex).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            monthDateList.add(plusDateStr);
            if(plusDateStr.equals(lastDateStr)) {
                break;
            }
            if(plusIndex >= 31) {
                break;
            }
            plusIndex++;
        }

        // firstDateStr, lastDateStr 기간으로 공휴일 테이블 조회
        List<String> holidayList = new ArrayList<>();

        List<HolidayInfoVO> result = new ArrayList<>();

        // sevenDateList 기준으로 공휴일(일요일 포함) 여부 체크해서 셋팅
        monthDateList.stream().forEach(dateStr ->
            {
                LocalDate checkedDate = LocalDate.parse(dateStr, formatter);
                int weekValue = checkedDate.getDayOfWeek().getValue();
                boolean isHoliday = false;
                boolean isSaturday = false;
                long existCount = holidayList.stream().filter(holidayStr -> holidayStr.equals(dateStr)).count();
                if(existCount > 0) {
                    isHoliday = true;
                }
                if(!isHoliday) {
                    if(weekValue == 7) {
                        isHoliday = true;
                    } else if(weekValue == 6) {
                        isSaturday = true;
                    }
                }
                HolidayInfoVO holidayInfoVO = HolidayInfoVO.builder()
                        .isHoliday(isHoliday)
                        .isSaturday(isSaturday)
                        .dateStr(dateStr)
                        .build();
                result.add(holidayInfoVO);
            }
        );
        log.info("checkHolidayByMonth : {}", result);
    }

    // 월의 1주 ~ 6주까지 각 주별로 기간일자 구하는 로직 체크
    @Test
    void getMonthWeekPeriodList() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse("202210" + "01", formatter);
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
        log.info("getMonthWeekPeriodList : {}", result);
    }

    // 어제 날짜 기준으로 이번주 월요일, 저번주 월요일 가져오기
    @Test
    void getTwoMondaybyYesterDay() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        int dayOfWeek = yesterday.getDayOfWeek().getValue();
        int mondayPeriodValue = dayOfWeek - 1;
        LocalDate thisWeekMondayDate = yesterday.minusDays(mondayPeriodValue);
        LocalDate beforeWeekMondayDate = yesterday.minusDays(mondayPeriodValue + 7);
        String result1 = thisWeekMondayDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String result2 = beforeWeekMondayDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("getTwoMondaybyYesterDay result1 : {}", result1);
        log.info("getTwoMondaybyYesterDay result2 : {}", result2);
    }

    // startDate와 endDate 기준으로 전체 목록 추출하기
    @Test
    void getDateListByStartEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse("20221201", formatter);
        LocalDate endDate = LocalDate.parse("20221207", formatter);
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
        log.info("getDateListByStartEndDate : {}", result);
    }

    // 주간 누적근무시간구하기
    @Test
    void weekSumWorkedTimeValue() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // group by로 user_id로 묶음
        Map<String, List<OfficeCommuteDayVO>> groupingMap =
                commuteDayVOList.stream().collect(Collectors.groupingBy(OfficeCommuteDayVO::getUserId));
        Set<String> userIds = groupingMap.keySet();

        // user_id 별로 반복함 ---> insert 단위임
        for (String userId : userIds) {
            OfficeCommuteWeekStatsVO officeCommuteWeekStatsVO =
                    OfficeCommuteWeekStatsVO.builder()
                            .userId(userId)
                            .build();
            List<OfficeCommuteDayVO> list = groupingMap.get(userId);

            list.stream().forEach(officeCommuteDayVO -> {
                String baseDateStr = officeCommuteDayVO.getBaseDateStr();
                LocalDate localDate = LocalDate.parse(baseDateStr, formatter);
                // base_date_str 가져와서 해당 요일을 가져옴. 해당 요일값에 setMethod에 셋팅함
                int weekValue = localDate.getDayOfWeek().getValue();
                switch (weekValue) {
                    case 1:
                        officeCommuteWeekStatsVO.setMonWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    case 2:
                        officeCommuteWeekStatsVO.setTueWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    case 3:
                        officeCommuteWeekStatsVO.setWedWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    case 4:
                        officeCommuteWeekStatsVO.setThuWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    case 5:
                        officeCommuteWeekStatsVO.setFriWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    case 6:
                        officeCommuteWeekStatsVO.setSatWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    case 7:
                        officeCommuteWeekStatsVO.setSunWorkTimeValue(officeCommuteDayVO.getWorkedTimeValue());
                        break;
                    default:
                        break;
                }
            });

            //-- vo에서 전체 값을 꺼내서 null 또는 0이 아닌 것들만 평균을 구함
            double sumWorkTimeValue = 0.0;
            if(officeCommuteWeekStatsVO.getMonWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getMonWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getMonWorkTimeValue();
            }
            if(officeCommuteWeekStatsVO.getTueWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getTueWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getTueWorkTimeValue();
            }
            if(officeCommuteWeekStatsVO.getWedWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getWedWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getWedWorkTimeValue();
            }
            if(officeCommuteWeekStatsVO.getThuWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getThuWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getThuWorkTimeValue();
            }
            if(officeCommuteWeekStatsVO.getFriWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getFriWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getFriWorkTimeValue();
            }
            if(officeCommuteWeekStatsVO.getSatWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getSatWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getSatWorkTimeValue();
            }
            if(officeCommuteWeekStatsVO.getSunWorkTimeValue() != null
                    && Double.compare(0.0, officeCommuteWeekStatsVO.getSunWorkTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteWeekStatsVO.getSunWorkTimeValue();
            }
            officeCommuteWeekStatsVO.setSumWorkTimeValue(sumWorkTimeValue);
            officeCommuteWeekStatsVO.setMondayStartDateStr("20221205");
            log.info("officeCommuteWeekStatsVO : {}", officeCommuteWeekStatsVO);
        }
    }

    // 월간 주단위(number) 누적근무시간구하기
    @Test
    void monthWeekSumWorkedTimeValue() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // group by로 user_id로 묶음
        Map<String, List<OfficeCommuteDayVO>> groupingMap =
                commuteDayVOList.stream().collect(Collectors.groupingBy(OfficeCommuteDayVO::getUserId));
        Set<String> userIds = groupingMap.keySet();

        // 해당 월의 주별 시작일자~종료일자 추출
        List<MonthWeekPeriodVO> monthWeekPeriodVOList = GWDateUtil.getMonthWeekPeriodListByMonth("202212");

        // user_id 별로 반복함 ---> insert 단위임
        for (String userId : userIds) {

            OfficeCommuteMonthDStatsVO officeCommuteMonthDStatsVO =
                    OfficeCommuteMonthDStatsVO.builder()
                            .userId(userId)
                            .build();

            List<OfficeCommuteDayVO> list = groupingMap.get(userId);

            list.stream().forEach(officeCommuteDayVO -> {
                String baseDateStr = officeCommuteDayVO.getBaseDateStr();
                double workedTimeValue = officeCommuteDayVO.getWorkedTimeValue();

                // 해당 baseDateStr이 어느 week에 해당하는지 확인하기
                int weekValue = monthWeekPeriodVOList.stream().filter(monthWeekPeriodVO -> {
                    long existCount = monthWeekPeriodVO.getDateStringList()
                            .stream().filter(dateStr -> dateStr.equals(baseDateStr)).count();
                    return existCount > 0 ? true : false;
                }).findAny().get().getWeek();

                // 각 주단위별로 근무시간 더하기
                switch (weekValue) {
                    case 1:
                        officeCommuteMonthDStatsVO.plusFirstWorkTimeValue(workedTimeValue);
                        break;
                    case 2:
                        officeCommuteMonthDStatsVO.plusSecondWorkTimeValue(workedTimeValue);
                        break;
                    case 3:
                        officeCommuteMonthDStatsVO.plusThreeWorkTimeValue(workedTimeValue);
                        break;
                    case 4:
                        officeCommuteMonthDStatsVO.plusFourWorkTimeValue(workedTimeValue);
                        break;
                    case 5:
                        officeCommuteMonthDStatsVO.plusFiveWorkTimeValue(workedTimeValue);
                        break;
                    case 6:
                        officeCommuteMonthDStatsVO.plusSixWorkTimeValue(workedTimeValue);
                        break;
                    default:
                        break;
                }
            });

            //-- vo에서 전체 값을 꺼내서 null 또는 0이 아닌 것들만 평균을 구함
            double sumWorkTimeValue = 0.0;
            if(officeCommuteMonthDStatsVO.getFirstWeekTimeValue() != null
                    && Double.compare(0.0, officeCommuteMonthDStatsVO.getFirstWeekTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteMonthDStatsVO.getFirstWeekTimeValue();
            }
            if(officeCommuteMonthDStatsVO.getSecondWeekTimeValue() != null
                    && Double.compare(0.0, officeCommuteMonthDStatsVO.getSecondWeekTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteMonthDStatsVO.getSecondWeekTimeValue();
            }
            if(officeCommuteMonthDStatsVO.getThreeWeekTimeValue() != null
                    && Double.compare(0.0, officeCommuteMonthDStatsVO.getThreeWeekTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteMonthDStatsVO.getThreeWeekTimeValue();
            }
            if(officeCommuteMonthDStatsVO.getFourWeekTimeValue() != null
                    && Double.compare(0.0, officeCommuteMonthDStatsVO.getFourWeekTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteMonthDStatsVO.getFourWeekTimeValue();
            }
            if(officeCommuteMonthDStatsVO.getFiveWeekTimeValue() != null
                    && Double.compare(0.0, officeCommuteMonthDStatsVO.getFiveWeekTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteMonthDStatsVO.getFiveWeekTimeValue();
            }
            if(officeCommuteMonthDStatsVO.getSixWeekTimeValue() != null
                    && Double.compare(0.0, officeCommuteMonthDStatsVO.getSixWeekTimeValue()) != 0) {
                sumWorkTimeValue = sumWorkTimeValue + officeCommuteMonthDStatsVO.getSixWeekTimeValue();
            }
            officeCommuteMonthDStatsVO.setSumWorkTimeValue(sumWorkTimeValue);
            officeCommuteMonthDStatsVO.setBaseMonthStr("202212");
            log.info("officeCommuteMonthDStatsVO : {}", officeCommuteMonthDStatsVO);
        }
    }

    // OFFICE_COMMUTE_MONTH_H_STATS : 통계_출퇴근_월간_휴일
    @Test
    void monthHolidaySumWorkedTimeValue() throws Exception {
        // 해당 월의 공휴일 목록을 가져옴

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse("202212" + "01", formatter);
        YearMonth monthDate = YearMonth.from(localDate);
        LocalDate firstDate = monthDate.atDay(1);
        LocalDate lastDate = monthDate.atEndOfMonth();
        String firstDateStr = firstDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String lastDateStr = lastDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<String> monthDateList = new ArrayList<>();
        int plusIndex = 0;
        while(true) {
            String plusDateStr = firstDate.plusDays(plusIndex).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            monthDateList.add(plusDateStr);
            if(plusDateStr.equals(lastDateStr)) {
                break;
            }
            if(plusIndex >= 31) {
                break;
            }
            plusIndex++;
        }

        // firstDateStr, lastDateStr 기간으로 공휴일 테이블 조회
        List<String> holidayList = new ArrayList<>();

        List<HolidayInfoVO> holidayInfoVOList = new ArrayList<>();

        // sevenDateList 기준으로 공휴일(일요일 포함) 여부 체크해서 셋팅
        monthDateList.stream().forEach(dateStr ->
                {
                    LocalDate checkedDate = LocalDate.parse(dateStr, formatter);
                    int weekValue = checkedDate.getDayOfWeek().getValue();
                    boolean isHoliday = false;
                    boolean isSaturday = false;
                    long existCount = holidayList.stream().filter(holidayStr -> holidayStr.equals(dateStr)).count();
                    if(existCount > 0) {
                        isHoliday = true;
                    }
                    if(!isHoliday) {
                        if(weekValue == 7) {
                            isHoliday = true;
                        } else if(weekValue == 6) {
                            isSaturday = true;
                        }
                    }
                    HolidayInfoVO holidayInfoVO = HolidayInfoVO.builder()
                            .isHoliday(isHoliday)
                            .isSaturday(isSaturday)
                            .dateStr(dateStr)
                            .build();
                    holidayInfoVOList.add(holidayInfoVO);
                }
        );

        // 해당 월의 공휴일 목록을 가져옴 end

        // 가져옴 공휴일 목록에서 날짜만 추출해서 List에 담음
        List<String> holidayStringList = holidayInfoVOList
                                .stream()
                                .map(vo -> vo.getDateStr())
                                .collect(Collectors.toList());

        // 사용자 단위로 일일_출퇴근 정보를 가져와서 userId 기준으로 groupBy 처리함

        // group by로 user_id로 묶음
        Map<String, List<OfficeCommuteDayVO>> groupingMap =
                commuteDayVOList.stream().collect(Collectors.groupingBy(OfficeCommuteDayVO::getUserId));
        Set<String> userIds = groupingMap.keySet();

        // user_id 별로 반복함 ---> insert 단위임
        for (String userId : userIds) {

            OfficeCommuteMonthHStatsVO officeCommuteMonthHStatsVO =
                    OfficeCommuteMonthHStatsVO.builder()
                            .userId(userId)
                            .build();

            List<OfficeCommuteDayVO> list = groupingMap.get(userId);

            list.stream().forEach(officeCommuteDayVO -> {

                final String baseDateStr = officeCommuteDayVO.getBaseDateStr();
                final double workedTimeValue = officeCommuteDayVO.getWorkedTimeValue();

                IntStream.range(0, holidayStringList.size())
                        .forEach(holidayIndex -> {
                            String holidayString = holidayStringList.get(holidayIndex);

                            if(baseDateStr.equals(holidayString)) {
                                Class officeCommuteMonthHStatsVOClass = null;
                                try {
                                    officeCommuteMonthHStatsVOClass = Class.forName(
                                            "com.yamdeng.template.vo.stats.OfficeCommuteMonthHStatsVO"
                                    );
                                } catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                Method method = null;
                                try {
                                    method = officeCommuteMonthHStatsVOClass.getMethod("setHd" + (holidayIndex+1) + "WorkTimeValue", Double.class);
                                } catch (NoSuchMethodException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    method.invoke(officeCommuteMonthHStatsVO, workedTimeValue);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                } catch (InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
            });

            //-- vo에서 전체 값을 꺼내서 null 또는 0이 아닌 것들만 평균을 구함
            double sumWorkTimeValue = 0.0;

            try {
                Class officeCommuteMonthHStatsVOClass = Class.forName(
                        "com.yamdeng.template.vo.stats.OfficeCommuteMonthHStatsVO"
                );
                for(int index=0; index<20; index++) {
                    Method method = officeCommuteMonthHStatsVOClass.getMethod("getHd" + (index+1) + "WorkTimeValue");
                    Object methodResult = method.invoke(officeCommuteMonthHStatsVO);
                    if(methodResult != null) {
                        double workTimeValue = (double) methodResult;
                        sumWorkTimeValue = sumWorkTimeValue + workTimeValue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            officeCommuteMonthHStatsVO.setSumWorkTimeValue(sumWorkTimeValue);
            officeCommuteMonthHStatsVO.setBaseMonthStr("202212");
            log.info("officeCommuteMonthHStatsVO : {}", officeCommuteMonthHStatsVO);
        }

    }

}
