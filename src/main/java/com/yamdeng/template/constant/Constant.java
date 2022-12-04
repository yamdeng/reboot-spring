package com.yamdeng.template.constant;

public interface Constant {

    // 출퇴근 상태 코드
    static final String CODE_WORK_STATUS_ING = "ING"; /* 업무중 */
    static final String CODE_WORK_STATUS_HOME_ING = "HOME_ING"; /* 재택중 */
    static final String CODE_WORK_STATUS_END = "END"; /* 업무종료 */
    static final String CODE_WORK_STATUS_VACATION_YEAR = "VACATION_YEAR"; /* 연차 */
    static final String CODE_WORK_STATUS_VACATION_MORNING = "VACATION_MORNING"; /* 오전반차 */
    static final String CODE_WORK_STATUS_VACATION_AFTERNOON = "VACATION_AFTERNOON"; /* 오후반차 */
    static final String CODE_WORK_STATUS_VACATION_NATION = "VACATION_NATION"; /* 공가 */
    static final String CODE_WORK_STATUS_VACATION_REPLACE = "VACATION_REPLACE"; /* 대체휴가 */
    static final String CODE_WORK_STATUS_VACATION_CON = "VACATION_CON"; /* 경조휴가 */
    static final String CODE_WORK_STATUS_VACATION_PRIZE = "VACATION_PRIZE"; /* 포상휴가 */
    static final String CODE_WORK_STATUS_VACATION_BABY = "VACATION_BABY"; /* 출산휴가 */
    static final String CODE_WORK_STATUS_VACATION_CARE = "VACATION_CARE"; /* 육아휴직 */
    static final String CODE_WORK_STATUS_VACATION_NORMAL = "VACATION_NORMAL"; /* 일반휴직 */
    static final String CODE_WORK_STATUS_VACATION_ETC = "VACATION_ETC"; /* 기타휴가 */

    // 출퇴근 결과 코드
    static final String CODE_WORK_RESULT_SUCCESS_NORMAL = "SUCCESS_NORMAL"; /* 정상출근 */
    static final String CODE_WORK_RESULT_SUCCESS_MORNING = "SUCCESS_MORNING"; /* 정상출근(오전반차) */
    static final String CODE_WORK_RESULT_SUCCESS_AFTERNOON = "SUCCESS_AFTERNOON"; /* 정상출근(오후반차) */
    static final String CODE_WORK_RESULT_SUCCESS_BIRTHDAY = "SUCCESS_BIRTHDAY"; /* 정상출근(생일) */
    static final String CODE_WORK_RESULT_TARDY = "TARDY"; /* 지각 */
    static final String CODE_WORK_RESULT_VACATION_TARDY_BIRTHDAY = "TARDY_BIRTHDAY"; /* 지각(생일) */
    static final String CODE_WORK_RESULT_VACATION_TARDY_MORNING = "TARDY_MORNING"; /* 지각(오전반차) */
    static final String CODE_WORK_RESULT_VACATION_TARDY_AFTERNOON = "TARDY_AFTERNOON"; /* 지각(오전반차) */
    static final String CODE_WORK_RESULT_VACATION_VACATION_YEAR = "VACATION_YEAR"; /* 연차 */
    static final String CODE_WORK_RESULT_VACATION_NATION = "VACATION_NATION"; /* 공가 */
    static final String CODE_WORK_RESULT_VACATION_REPLACE = "VACATION_REPLACE"; /* 대체휴가 */
    static final String CODE_WORK_RESULT_VACATION_CON = "VACATION_CON"; /* 경조휴가 */
    static final String CODE_WORK_RESULT_VACATION_PRIZE = "VACATION_PRIZE"; /* 포상휴가 */
    static final String CODE_WORK_RESULT_VACATION_BABY = "VACATION_BABY"; /* 출산휴가 */
    static final String CODE_WORK_RESULT_VACATION_CARE = "VACATION_CARE"; /* 육아휴직 */
    static final String CODE_WORK_RESULT_VACATION_NORMAL = "VACATION_NORMAL"; /* 일반휴직 */
    static final String CODE_WORK_RESULT_VACATION_ETC = "VACATION_ETC"; /* 기타휴가 */
    static final String CODE_WORK_RESULT_ABSENT = "ABSENT"; /* 결근 */
    static final String CODE_WORK_RESULT_ETC = "ETC"; /* 기타 */

    // 휴가종류 코드
    static final String CODE_VACATION_KIND_VACATION_YEAR = "VACATION_YEAR"; /* 연차 */
    static final String CODE_VACATION_KIND_VACATION_MORNING = "VACATION_MORNING"; /* 오전반차 */
    static final String CODE_VACATION_KIND_VACATION_AFTERNOON = "VACATION_AFTERNOON"; /* 오후반차 */
    static final String CODE_VACATION_KIND_VACATION_NATION = "VACATION_NATION"; /* 공가 */
    static final String CODE_VACATION_KIND_VACATION_REPLACE = "VACATION_REPLACE"; /* 대체휴가 */
    static final String CODE_VACATION_KIND_VACATION_CON = "VACATION_CON"; /* 경조휴가 */
    static final String CODE_VACATION_KIND_VACATION_PRIZE = "VACATION_PRIZE"; /* 포상휴가 */
    static final String CODE_VACATION_KIND_VACATION_BABY = "VACATION_BABY"; /* 출산휴가 */
    static final String CODE_VACATION_KIND_VACATION_CARE = "VACATION_CARE"; /* 육아휴직 */
    static final String CODE_VACATION_KIND_VACATION_NORMAL = "VACATION_NORMAL"; /* 일반휴직 */
    static final String CODE_VACATION_KIND_ETC = "VACATION_ETC"; /* 기타휴가 */

    // 부서 출퇴근 제출 상태 코드
    static final String CODE_COMMUTE_DEPT_STATUS_SUBMIT = "SUBMIT"; /* 제출 */
    static final String CODE_COMMUTE_DEPT_STATUS_APPROVE = "APPROVE"; /* 승인 */
    static final String CODE_COMMUTE_DEPT_STATUS_REJECT = "REJECT"; /* 반려 */

    // 업무보고 제출 상태 코드
    static final String CODE_REPORT_STATUS_SUBMIT = "SUBMIT"; /* 제출 */
    static final String CODE_REPORT_STATUS_APPROVE = "APPROVE"; /* 승인 */
    static final String CODE_REPORT_STATUS_REJECT = "REJECT"; /* 반려 */

}
