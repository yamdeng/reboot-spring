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
public class OfficeVacationDetailDayHistoryVO extends BaseCommonVO {

    private String userId; /* 직원ID */
    private String vacationKindCode; /* 휴가종류 */
    private String baseDateStr; /* 휴가일(일별) */
    private String approveId; /* 전자결재 연동ID */

}
