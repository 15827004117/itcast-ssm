package com.ssm.service.impl;

import com.ssm.dao.IRoleDao;
import com.ssm.domain.Role;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public Role findRoleByIdAndAllPermission(String id) throws Exception {
        return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId: permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
