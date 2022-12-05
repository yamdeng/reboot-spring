package com.yamdeng.template.vo.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatsCommonVO {

    private String kind; /* 통계 종류 */
    private Integer aggCount; /* 통계 count */

}
