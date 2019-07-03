package com.ssm.service;

import com.ssm.domain.Orders;

import java.util.List;

public interface IOrderService {

    // 查询所有产品信息
    List<Orders> findAll() throws Exception;
}
