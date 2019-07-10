package com.ssm.dao;

import com.ssm.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users")
    List<User> findAll () throws Exception;

    @Insert("insert into users (username,password,email,phoneNum,status) values (#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(User user);

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "status", property = "status"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class,
                    many = @Many(select = "com.ssm.dao.IRoleDao.findByUserId"))
    })
    User findByUsername (String username) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "status", property = "status"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class,
                    many = @Many(select = "com.ssm.dao.IRoleDao.findByUserId"))
    })
    User findById(String id) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "status", property = "status"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class,
                    many = @Many(select = "com.ssm.dao.IRoleDao.findRoleByNotUserId"))
    })
    User findUserByIdAndAllRole(String id) throws Exception;

    @Insert("insert into users_role (userId, roleId) values (#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
