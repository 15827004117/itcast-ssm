package com.ssm.dao;

import com.ssm.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {

    @Select("select * from users")
    List<User> findAll () throws Exception;
}
