package com.ssm.service;

import com.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll() throws Exception;

    Role findRoleByIdAndAllPermission(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
