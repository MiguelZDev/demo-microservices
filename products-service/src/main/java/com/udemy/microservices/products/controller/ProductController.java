package com.udemy.microservices.products.controller;

import com.udemy.microservices.products.entity.Product;
import com.udemy.microservices.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/products")
    public List<Product> getAll() {
        return service.findAll();
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable Long id) {
        return service.findById(id);
    }

}
