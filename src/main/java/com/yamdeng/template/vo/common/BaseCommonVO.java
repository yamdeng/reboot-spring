package com.yamdeng.template.vo.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class BaseCommonVO {

    private String userKey; /* 사용자키 */
    private String userName; /* 사용자이름 */
    private String mobileTel; /* 핸드폰번호 */
    private String email; /* 이메일 */
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
    private Integer pageSize; /* grid 페이징 size(offset 계산시 사용함 : pageSize - 1 * 10) */
    private String searchDateStr; /* 조회시작일(요청시만 사용) */
    private String searchMonthStr; /* 조회월(요청시만 사용) */
    private String searchYearStr; /* 조회년(요청시만 사용) */
    private String startDateStr; /* 조회시작일(요청시만 사용) */
    private String endDateStr; /* 조회종료일(요청시만 사용) */
    private List<String> childDeptIdList; /* 하위 부서ID 목록 */
    private String beforeWorkDateStr; /* 이전평일 */
    private String twoBeforeWorkDateStr; /* 이전전평일 */
    private String managerMobileName; /* 팀장명 */
    private String managerMobileTel; /* 팀장연락처 */

}
