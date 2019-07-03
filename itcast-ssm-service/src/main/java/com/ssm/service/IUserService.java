package com.ssm.service;

import com.ssm.domain.User;

import java.util.List;

public interface IUserService {

    // 查询用户列表
    List<User> findAll () throws Exception;
}
