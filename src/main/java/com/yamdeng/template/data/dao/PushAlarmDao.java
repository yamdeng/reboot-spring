package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PushAlarmDao {

    // 읽지않은 알림 count
    int unreadCountByUserId(String userId);

    // 이미 알림 테이블에 저장하였는지 확인
    int checkAlreadySave(OfficePushAlarmVO vo);

    // 알림 등록
    int insertPushAlarm(OfficePushAlarmVO vo);

}
