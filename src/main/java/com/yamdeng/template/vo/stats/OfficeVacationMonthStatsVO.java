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
public class OfficeVacationMonthStatsVO extends BaseCommonVO  {

    private String userId; /* 직원ID */
    private String baseYear; /* 기준년 */

    @Builder.Default
    private Double use1monthCount = 0.0; /* 1월 사용일수 */
    @Builder.Default
    private Double use2monthCount = 0.0; /* 2월 사용일수 */
    @Builder.Default
    private Double use3monthCount = 0.0; /* 3월 사용일수 */
    @Builder.Default
    private Double use4monthCount = 0.0; /* 4월 사용일수 */
    @Builder.Default
    private Double use5monthCount = 0.0; /* 5월 사용일수 */
    @Builder.Default
    private Double use6monthCount = 0.0; /* 6월 사용일수 */
    @Builder.Default
    private Double use7monthCount = 0.0; /* 7월 사용일수 */
    @Builder.Default
    private Double use8monthCount = 0.0; /* 8월 사용일수 */
    @Builder.Default
    private Double use9monthCount = 0.0; /* 9월 사용일수 */
    @Builder.Default
    private Double use10monthCount = 0.0; /* 10월 사용일수 */
    @Builder.Default
    private Double use11monthCount = 0.0; /* 11월 사용일수 */
    @Builder.Default
    private Double use12monthCount = 0.0; /* 12월 사용일수 */

}
