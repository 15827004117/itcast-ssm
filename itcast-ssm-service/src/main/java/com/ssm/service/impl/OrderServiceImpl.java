package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IOrderDao;
import com.ssm.domain.Orders;
import com.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        return orderDao.findAll();
    }
}
