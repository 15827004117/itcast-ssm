package com.ssm.controller;

import com.ssm.domain.User;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    // 查询用户列表
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        try {
            List<User> list = userService.findAll();
            mv.addObject("userList", list);
            mv.setViewName("user-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    // 添加用户
    @RequestMapping("/save.do")
    public String save(User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }

    // 查询用户详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id")String id) {
        ModelAndView mv = new ModelAndView();
        User user = null;
        try {
             user = userService.findById(id);
             mv.addObject("user",user);
             mv.setViewName("user-show");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    // 查询该用户可添加的角色选项
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id")String id) {
        ModelAndView mv = new ModelAndView();
        try {
            // 获得该用户及其可以添加的角色项
            User user = userService.findUserByIdAndAllRole(id);
            mv.addObject("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("user-role-add");
        return mv;
    }

    // 用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String  addRoleToUser(@RequestParam(name = "userId")String userId,
                              @RequestParam(name = "ids")String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

}
