package com.yamdeng.template.vo.db;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OfficeVacationYearVO extends BaseCommonVO {

    private String userId; /* 직원ID */
    private String baseYear; /* 기준년 */
    private Double annualCount; /* 발생연차 */
    private Double monthCount; /* 금년월차 */
    private Double useableCount; /* 사용가능일수 */
    private Double plusVacationCount; /* 포상휴가 수 */
    private Double usedCount; /* 사용일수 */

}
