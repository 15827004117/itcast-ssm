package com.ssm.dao;

import com.ssm.domain.Member;
import com.ssm.domain.Orders;
import com.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

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

    // 根据id查询订单详情
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class,
                    one = @One(select = "com.ssm.dao.IProductDao.findById")),   // 关联查询订单详情
            @Result(column = "memberId", property = "member", javaType = Member.class,
                    one = @One(select = "com.ssm.dao.IMemberDao.findById")),     // 关联查询会员信息
            @Result(column = "id", property = "travellers", javaType = java.util.List.class,
                    many = @Many(select = "com.ssm.dao.ITravellersDao.findByOrdersId")) // 关联查询旅客信息
    })
    Orders findById(String id) throws Exception;
}
