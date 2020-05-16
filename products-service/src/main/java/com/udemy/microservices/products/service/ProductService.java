package com.udemy.microservices.products.service;

import com.udemy.microservices.products.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);

}
