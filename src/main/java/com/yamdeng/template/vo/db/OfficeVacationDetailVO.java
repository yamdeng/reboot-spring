package com.yamdeng.template.vo.db;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeVacationDetailVO extends BaseCommonVO {

    private String userId; /* 직원ID */
    private String baseYear; /* 기준년 */
    private LocalDateTime submitDate; /* 신청일 */
    private LocalDateTime approveDate; /* 승인일 */
    private String vacationKindCode; /* 휴가종류 */
    private String vacationKindCodeName; /* 휴가종류코드명 */
    private String vacationStartDateStr; /* 휴가시작기간 */
    private String vacationEndDateStr; /* 휴가종료기간 */
    private Double useCount; /* 사용연차 수 */
    private String vacationDescription; /* 휴가/휴직 사유 */
    private String approveId; /* 전자결재 연동ID */

}
