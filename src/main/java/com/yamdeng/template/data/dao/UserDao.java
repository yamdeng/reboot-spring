package com.yamdeng.template.data.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    // 부서의 팀장 사용자ID 가져오기
    String getDeptManagerUserId(String deptId);

}
