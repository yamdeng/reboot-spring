package com.yamdeng.template.vo.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@SuperBuilder
public class BaseCommonVO {

    private String userKey; /* 사용자키 */
    private String userName; /* 사용자이름 */
    private String deptKey; /* 부서키 */
    private String deptName; /* 부서명 */
    private String rankKey; /* 직급키 */
    private String dutyKey; /* 직책키 */
    private String positionKey; /* 직위키 */
    private String rankTitle; /* 직급명 */
    private String dutyTitle; /* 직책명 */
    private String positionTitle; /* 직위명 */
    private LocalDateTime regDate; /* 등록일 */
    private LocalDateTime modDate; /* 수정일 */
    private String regUserId; /* 등록자ID */
    private String modUserId; /* 수정자ID */
    private String loginUserId; /* 로그인ID */
    private Integer limit; /* 페이징 시작 */
    private Integer offset; /* 페이징 종료 */

}
