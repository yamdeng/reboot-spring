package com.yamdeng.template.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCommonVO {

    private String userName; /* 사용자이름 */
    private String deptKey; /* 부서키 */
    private String deptName; /* 부서명 */
    private String rankTitle; /* 직급명 */
    private String dutyTitle; /* 직책명 */
    private String positionTitle; /* 직위명 */
    private LocalDateTime regDate; /* 등록일 */
    private LocalDateTime modDate; /* 수정일 */
    private String regUserId; /* 등록자ID */
    private String modUserId; /* 수정자ID */

}
