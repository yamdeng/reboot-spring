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
public class OfficePushAlarmVO extends BaseCommonVO {

    private String alarmId; /* 알림_ID */
    private String baseDateStr; /* 기준일 */
    private String userId; /* 대상_사용자_ID */
    private String alarmTitle; /* 제목 */
    private String alarmContent; /* 내용 */
    private String alarmKindCode; /* 알림구분(공지, 출퇴근) */
    private LocalDateTime regDate; /* 등록일 */
    private LocalDateTime readDate; /* 읽은날짜 */
    private String linkYn; /* 링크여부 */
    private String linkUrl; /* 링크URL */
    private String targetId; /* 대상ID */
    private LocalDateTime modDate; /* 수정일 */

}
