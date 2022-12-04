package com.yamdeng.template.data.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    String getDeptManagerUserId(String deptId);

}
