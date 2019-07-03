package com.ssm.dao;

import com.ssm.domain.Orders;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderDao {

    @Select("select * from orders")
    List<Orders> findAll() throws Exception;
}
