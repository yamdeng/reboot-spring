package com.yamdeng.template.vo.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MonthWeekPeriodVO {

    // 1~6주 기준이 되는 value
    private Integer week;

    // 주의 시작일자
    private String startDateStr;

    // 주의 종료일자
    private String endDateStr;

    // 시작일자 ~ 종료일자 전체 list
    private List<String> dateStringList;

}
