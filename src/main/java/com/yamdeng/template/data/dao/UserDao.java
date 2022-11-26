package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<UserVO> test();

}
