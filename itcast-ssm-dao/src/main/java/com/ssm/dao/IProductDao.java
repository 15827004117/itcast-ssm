package com.ssm.dao;

import com.ssm.domain.Product;

import java.util.List;

public interface IProductDao {

    // 查询所有产品信息
    public List<Product> findAll() throws Exception;
}
