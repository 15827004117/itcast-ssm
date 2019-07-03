package com.ssm.controller;

import com.ssm.domain.Orders;
import com.ssm.domain.Product;
import com.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    // 查看订单列表
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        try {
            List<Orders> list = orderService.findAll();
            mv.addObject("ordersList", list);
            mv.setViewName("orders-list");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
