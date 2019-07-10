package com.ssm.dao;

import com.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.ssm.dao.IPermissionsDao.findByRoleId"))
    })
    List<Role> findByUserId(String id) throws  Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findRoleByNotUserId(String id) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.ssm.dao.IPermissionsDao.findPermissionByNotRoleId"))
    })
    Role findRoleByIdAndAllPermission(String id) throws Exception;

    @Insert("insert into role_permission (permissionId, roleId) values (#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
