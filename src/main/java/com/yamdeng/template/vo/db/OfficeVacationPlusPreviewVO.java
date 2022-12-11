package com.yamdeng.template.vo.db;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeVacationPlusPreviewVO extends BaseCommonVO {

    private String userId; /* 직원ID */
    private String baseYear; /* 기준년 */
    private String vacationName; /* 휴가이름 */
    private Double vacationCount; /* 휴가일 수 */
    private String searchKind; /* 입사일 기준 1년 이상인 직원(1), 입사일 기준 10년 이상인 직원(10) */

}
