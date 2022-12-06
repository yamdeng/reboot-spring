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
public class OfficeCommuteDeptDayVO extends BaseCommonVO {

    private String baseDateStr; /* 제출기준일 */
    private String deptId; /* 제출부서 */
    private String userId; /* 제출자(팀장) */
    private LocalDateTime submitDate; /* 제출일 */
    private String modYn; /* 수정여부 : 화면에서(팀원 출퇴근 일괄수정시) 전달받음 */
    private String tardyYn; /* 지각여부 : 제출시 반영 */
    private String commuteSubmitStatusCode; /* 출/퇴근 제출상태 : 제출시 반영 */
    private String commuteSubmitStatusCodeName; /* 출/퇴근 제출상태 코드 명 */
    private Integer targetCount; /* 제출대상수 : 제출시 반영*/
    private Integer startWorkCompleteCount; /* 출근완료 합계 : 제출시 반영 */
    private Integer outWorkCompleteCount; /* 퇴근완료 합계 : 제출시 반영 */

}
