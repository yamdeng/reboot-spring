package com.yamdeng.template.vo.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OfficeCommuteDayVO {

    private String baseDateStr; /* 근태기준일 */
    private String userId; /* 직원ID */
    private LocalDateTime startWorkDate; /* 최초출근시간 */
    private LocalDateTime outWorkDate; /* 최초퇴근시간 */
    private LocalDateTime finalStartWorkDate; /* 최종출근시간 */
    private LocalDateTime finalOutWorkDate; /* 최종퇴근시간 */
    private String startWorkIp; /* 출근IP */
    private String outWorkIp; /* 퇴근IP */
    private String outsideWorkYn; /* 외근여부 */
    private String workStatusCode; /* 근무상태 */
    private String workResultCode; /* 근무결과 */
    private String vacationKindCode; /* 휴가종류 */
    private String etcDescription; /* 기타설명 */
    private String modYn; /* 수정여부 */
    private String tardyYn; /* 지각여부 */
    private Double workedTimeValue; /* 근무시간 */
    private LocalDateTime regDate; /* 등록일 */
    private LocalDateTime modDate; /* 수정일 */
    private String regUserId; /* 등록자ID */
    private String modUserId; /* 수정자ID */

}
