package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Orders;
import com.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    // 查看订单列表
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size", required = true, defaultValue = "5")int size){
        ModelAndView mv = new ModelAndView();
        try {
            List<Orders> list = orderService.findAll(page, size);
            PageInfo pageInfo = new PageInfo(list);
            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("orders-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
