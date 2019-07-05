package com.ssm.service.impl;

import com.ssm.dao.IUserDao;
import com.ssm.domain.Role;
import com.ssm.domain.User;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 查询用户列表
    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    // 添加用户
    @Override
    public void save(User user) throws Exception {
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword())) ;
        userDao.save(user);
    }

    // 查询用户详情
    @Override
    public User findById(String id) throws Exception {
        return userDao.findById(id);
    }

    // 用户登陆验证
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userInfo = null;
        try {
            // 数据库中查询出user
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 调用security的user
        org.springframework.security.core.userdetails.User user
                = new org.springframework.security.core.userdetails.User(userInfo.getUsername(),userInfo.getPassword()
                ,userInfo.getStatus() == 0 ? false:true, true, true, true
                ,getAuthority(userInfo.getRoles()));
        return user;
    }

    // 获得用户角色权限
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        //roles.stream().forEach(s -> list.add(new SimpleGrantedAuthority("ROLE" + s.getRoleName())));
        for (Role role: roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

}
