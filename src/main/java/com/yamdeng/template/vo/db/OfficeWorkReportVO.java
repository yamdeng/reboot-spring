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
public class OfficeWorkReportVO extends BaseCommonVO {

    private String reportId; /* 업부보고_ID */
    private String baseDateStr; /* 보고기준일 */
    private LocalDateTime reportDate; /* 보고일(작성일) */
    private String userId; /* 작성자 */
    private String deptId; /* 부서 */
    private String reportContent; /* 보고내용 */
    private String reportSubmitStatusCode; /* 업무보고 제출상태 */
    private String issueYn; /* 이슈 여부 */
    private String searchKind; /* 검색종류 : 업무보고(ALL), 제출(SUBMIT), 미제출(NOT_SUBMIT), 이슈(ISSUE), 댓글(COMMENT) */

}
