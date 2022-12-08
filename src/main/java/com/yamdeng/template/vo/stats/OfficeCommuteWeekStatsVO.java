package com.yamdeng.template.vo.stats;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeCommuteWeekStatsVO extends BaseCommonVO  {

    private String userId; /* 직원ID */
    private String mondayStartDateStr; /* 월요일 시작 일 */

    @Builder.Default
    private Double sumWorkTimeValue = 0.0; /* 누적근무시간 */

    @Builder.Default
    private Double monWorkTimeValue = 0.0; /* 월 근무시간 */

    @Builder.Default
    private Double tueWorkTimeValue = 0.0; /* 화 근무시간 */

    @Builder.Default
    private Double wedWorkTimeValue = 0.0; /* 수 근무시간 */

    @Builder.Default
    private Double thuWorkTimeValue = 0.0; /* 목 근무시간 */

    @Builder.Default
    private Double friWorkTimeValue = 0.0; /* 금 근무시간 */

    @Builder.Default
    private Double satWorkTimeValue = 0.0; /* 토 근무시간 */

    @Builder.Default
    private Double sunWorkTimeValue = 0.0; /* 일 근무시간 */

}
