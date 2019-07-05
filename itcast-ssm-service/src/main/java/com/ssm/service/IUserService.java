package com.ssm.service;

import com.ssm.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    // 查询用户列表
    List<User> findAll () throws Exception;

    // 添加
    void save(User user) throws Exception;

    // 查询用户详情
    User findById(String id) throws Exception;
}
