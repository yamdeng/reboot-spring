package com.yamdeng.template.data.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PushAlarmDao {

    // 읽지읂은 알림 count
    int unreadCountByUserId(String userId);

}
