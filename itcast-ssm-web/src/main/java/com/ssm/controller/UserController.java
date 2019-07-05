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

}
