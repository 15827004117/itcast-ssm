package com.ssm.service;

import com.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    // 查询所有产品信息
    List<Product> findAll() throws Exception;

    // 添加产品信息
    void save(Product product) throws Exception;

}

