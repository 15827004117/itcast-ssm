package com.ssm.dao;

import com.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    // 根据id查询会员信息
    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;
}
