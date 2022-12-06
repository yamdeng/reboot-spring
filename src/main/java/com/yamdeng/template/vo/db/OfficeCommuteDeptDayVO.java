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
    private String modYn; /* 수정여부 */
    private String tardyYn; /* 지각여부 */
    private String commuteSubmitStatusCode; /* 출/퇴근 제출상태 */
    private String commuteSubmitStatusCodeName; /* 출/퇴근 제출상태 코드 명*/
    private Integer targetCount; /* 제출대상수 */
    private Integer startWorkCompleteCount; /* 출근완료 합계 */
    private Integer outWorkCompleteCount; /* 퇴근완료 합계 */

}
