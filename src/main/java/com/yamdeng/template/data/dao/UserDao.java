package com.yamdeng.template.data.dao;

import com.yamdeng.template.vo.common.BaseCommonVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    // 부서의 팀장 사용자ID 가져오기
    String getDeptManagerUserId(String deptId);

    // 하위 부서 목록 가져오기
    List<BaseCommonVO> selectChildDeptListByDeptKey(String deptKey);

}
