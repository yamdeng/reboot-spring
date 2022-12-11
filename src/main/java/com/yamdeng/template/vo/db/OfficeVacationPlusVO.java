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
public class OfficeVacationPlusVO extends BaseCommonVO {

    private String userId; /* 직원ID */
    private String baseYear; /* 기준년 */
    private String vacationName; /* 휴가이름 */
    private Double vacationCount; /* 휴가일 수 */

}
