package com.yamdeng.template.vo.db;

import com.yamdeng.template.vo.common.BaseCommonVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeVacationYearPreviewVO extends BaseCommonVO {

    private String userId; /* 직원ID */
    private String baseYear; /* 기준년 */
    private Double annualCount; /* 발생연차 */
    private Double monthCount; /* 금년월차 */
    private Double useableCount; /* 사용가능일수 */
    private Double plusVacationCount; /* 포상휴가 수 */
    private Double usedCount; /* 사용일수 */
    private String newEmployeeYn; /* 휴가발생데이터가 없는 직원(신규사용자 조회 여부) */
    private List<String> userIdList; /* 선택한 사용자ID 목록 */

}
