package com.ssm.controller;

import com.ssm.domain.Permission;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        try {
            List<Permission> list = permissionService.findAll();
            mv.addObject("permissionList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("permission-list");
        return mv;
    }
}
