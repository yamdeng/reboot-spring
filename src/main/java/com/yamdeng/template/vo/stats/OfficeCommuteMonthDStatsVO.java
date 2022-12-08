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
public class OfficeCommuteMonthDStatsVO extends BaseCommonVO  {

    private String userId; /* 직원ID */
    private String baseMonthStr; /* 기준년월 */

    @Builder.Default
    private Double sumWorkTimeValue = 0.0; /* 누적근무시간 */

    @Builder.Default
    private Double firstWeekTimeValue = 0.0; /* 1주 근무시간 */

    @Builder.Default
    private Double secondWeekTimeValue = 0.0; /* 2주 근무시간 */

    @Builder.Default
    private Double threeWeekTimeValue = 0.0; /* 3주 근무시간 */

    @Builder.Default
    private Double fourWeekTimeValue = 0.0; /* 4주 근무시간 */

    @Builder.Default
    private Double fiveWeekTimeValue = 0.0; /* 5주 근무시간 */

    @Builder.Default
    private Double sixWeekTimeValue = 0.0; /* 6주 근무시간 */

    public void plusFirstWorkTimeValue(double workTimeValue) {
        this.firstWeekTimeValue = this.firstWeekTimeValue + workTimeValue;
    }

    public void plusSecondWorkTimeValue(double workTimeValue) {
        this.secondWeekTimeValue = this.secondWeekTimeValue + workTimeValue;
    }

    public void plusThreeWorkTimeValue(double workTimeValue) {
        this.threeWeekTimeValue = this.threeWeekTimeValue + workTimeValue;
    }

    public void plusFourWorkTimeValue(double workTimeValue) {
        this.fourWeekTimeValue = this.fourWeekTimeValue + workTimeValue;
    }

    public void plusFiveWorkTimeValue(double workTimeValue) {
        this.fiveWeekTimeValue = this.fiveWeekTimeValue + workTimeValue;
    }

    public void plusSixWorkTimeValue(double workTimeValue) {
        this.sixWeekTimeValue = this.sixWeekTimeValue + workTimeValue;
    }

}
