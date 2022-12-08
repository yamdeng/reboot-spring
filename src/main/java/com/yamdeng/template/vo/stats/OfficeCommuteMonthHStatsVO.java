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
public class OfficeCommuteMonthHStatsVO extends BaseCommonVO  {

    private String userId; /* 직원ID */
    private String baseMonthStr; /* 기준년월 */

    @Builder.Default
    private Double sumWorkTimeValue = 0.0; /* 누적근무시간 */
    @Builder.Default
    private Double hd1WorkTimeValue = 0.0; /* 휴일 1일째 근무시간 */
    @Builder.Default
    private Double hd2WorkTimeValue = 0.0; /* 휴일 2일째 근무시간 */
    @Builder.Default
    private Double hd3WorkTimeValue = 0.0; /* 휴일 3일째 근무시간 */
    @Builder.Default
    private Double hd4WorkTimeValue = 0.0; /* 휴일 4일째 근무시간 */
    @Builder.Default
    private Double hd5WorkTimeValue = 0.0; /* 휴일 5일째 근무시간 */
    @Builder.Default
    private Double hd6WorkTimeValue = 0.0; /* 휴일 6일째 근무시간 */
    @Builder.Default
    private Double hd7WorkTimeValue = 0.0; /* 휴일 7일째 근무시간 */
    @Builder.Default
    private Double hd8WorkTimeValue = 0.0; /* 휴일 8일째 근무시간 */
    @Builder.Default
    private Double hd9WorkTimeValue = 0.0; /* 휴일 9일째 근무시간 */
    @Builder.Default
    private Double hd10WorkTimeValue = 0.0; /* 휴일 10일째 근무시간 */
    @Builder.Default
    private Double hd11WorkTimeValue = 0.0; /* 휴일 11일째 근무시간 */
    @Builder.Default
    private Double hd12WorkTimeValue = 0.0; /* 휴일 12일째 근무시간 */
    @Builder.Default
    private Double hd13WorkTimeValue = 0.0; /* 휴일 13일째 근무시간 */
    @Builder.Default
    private Double hd14WorkTimeValue = 0.0; /* 휴일 14일째 근무시간 */
    @Builder.Default
    private Double hd15WorkTimeValue = 0.0; /* 휴일 15일째 근무시간 */
    @Builder.Default
    private Double hd16WorkTimeValue = 0.0; /* 휴일 16일째 근무시간 */
    @Builder.Default
    private Double hd17WorkTimeValue = 0.0; /* 휴일 17일째 근무시간 */
    @Builder.Default
    private Double hd18WorkTimeValue = 0.0; /* 휴일 18일째 근무시간 */
    @Builder.Default
    private Double hd19WorkTimeValue = 0.0; /* 휴일 19일째 근무시간 */
    @Builder.Default
    private Double hd20WorkTimeValue = 0.0; /* 휴일 20일째 근무시간 */

}
