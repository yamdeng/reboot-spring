package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.db.OfficePushAlarmVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PushAlarmDao {

    // 읽지읂은 알림 count
    int unreadCountByUserId(String userId);

    int checkAlreadySave(OfficePushAlarmVO vo);

    int insertPushAlarm(OfficePushAlarmVO vo);

}
