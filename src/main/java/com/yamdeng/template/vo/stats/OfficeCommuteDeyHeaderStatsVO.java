package com.yamdeng.template.vo.stats;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeCommuteDeyHeaderStatsVO extends BaseCommonVO {

    private Integer successCommuteCount; /* 정상출근 수 */
    private Integer tardyCommuteCount; /* 지각 수 */
    private Integer vacationCount; /* 휴가/휴직 수 */
    private Double avgWorkedTimeValue; /* 평균근무시간 */

}
