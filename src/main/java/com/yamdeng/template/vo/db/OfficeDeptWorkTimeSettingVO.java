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
public class OfficeDeptWorkTimeSettingVO extends BaseCommonVO {

    private String deptId; /* 적용부서ID */
    private String timeName; /* 근무시간명 */
    private String timeDescription; /* 근무시간 설명 */
    private String workLocation; /* 근무지 */
    private String workStartTime; /* 근무시작시간 */
    private String workEndTime; /* 근무종료시간 */
    private String lunchStartTime; /* 점심시작시간 */
    private String lunchEndTime; /* 점심종료시간 */
    private String applyStartDateStr; /* 적용시작일 */
    private String applyEndDateStr; /* 적용종료일 */
    private LocalDateTime regDate; /* 등록일 */
    private LocalDateTime modDate; /* 수정일 */
    private String regUserId; /* 등록자ID */
    private String modUserId; /* 수정자ID */

}
