package com.ssm.dao;

import com.ssm.domain.Orders;
import com.ssm.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderDao {

    // 查询全部订单信息
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class,
                    one = @One(select = "com.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;
}
