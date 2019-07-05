package com.ssm.dao;

import com.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionsDao {

    // 根据角色id查询权限信息
    @Select("select * from permission where id in ( select permissionId from role_permission where roleId = #{id})")
    List<Permission> findByRoleId(String id) throws Exception;
}
