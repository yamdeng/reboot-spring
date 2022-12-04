package com.yamdeng.template.vo.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeVacationDetailDayHistoryVO {

    private String userId; /* 직원ID */
    private LocalDateTime regDate; /* 신청일 */
    private String vacationKindCode; /* 휴가종류 */
    private String baseDateStr; /* 휴가일(일별) */
    private String approveId; /* 전자결재 연동ID */

}
