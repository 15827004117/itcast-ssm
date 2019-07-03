package com.ssm.service;

import com.ssm.domain.Orders;

import java.util.List;

public interface IOrderService {

    // 查询所有产品信息
    List<Orders> findAll(int page, int size) throws Exception;

    // 根据id查询详情信息
    Orders findById(String ordersId) throws Exception;
}
