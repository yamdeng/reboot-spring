package com.yamdeng.template.data.mapper;

import com.yamdeng.template.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserVO> test();

}
