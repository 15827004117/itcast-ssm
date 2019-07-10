package com.ssm.controller;

import com.ssm.domain.Role;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    // 查看角色列表
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        try {
            List<Role> list = roleService.findAll();
            mv.addObject("roleList", list);
            mv.setViewName("role-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    // 查看该角色及可添加的权限项
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id")String id) {
        ModelAndView mv = new ModelAndView();
        try {
            Role role = roleService.findRoleByIdAndAllPermission(id);
            mv.addObject("role", role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("role-permission-add");
        return mv;
    }

    // 角色添加权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId")String roleId,
                                      @RequestParam(name = "ids")String[] permissionIds) {
        try {
            roleService.addPermissionToRole(roleId, permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }
}
