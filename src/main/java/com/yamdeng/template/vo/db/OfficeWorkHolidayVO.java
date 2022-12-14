package com.yamdeng.template.vo.db;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeWorkHolidayVO extends BaseCommonVO {

    private String baseYear; /* 기준년 */
    private String holiDateStr; /* 날짜 */
    private String name; /* 명칭 */
    private String weekdayCode; /* 요일구분 */
    private String weekdayCodeName; /* 요일구분 코드명 */
    private String weekendCode; /* 주중/주말 구분 */
    private String weekendCodeName; /* 주중/주말 구분 코드명 */

}
