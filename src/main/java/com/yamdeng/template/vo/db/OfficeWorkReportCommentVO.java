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
public class OfficeWorkReportCommentVO extends BaseCommonVO {

    private String commentId; /* 댓글_ID */
    private String reportId; /* 업부보고_ID */
    private String userId; /* 작성자 */
    private String commentContent; /* 댓글내용 */

}
