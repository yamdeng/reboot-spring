package com.yamdeng.template.vo.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HolidayInfoVO {

    // 날짜
    private String dateStr;

    // 공휴일 여부(일요일 or 공휴일)
    private boolean isHoliday;

    // 토요일 여부
    private boolean isSaturday;

}
