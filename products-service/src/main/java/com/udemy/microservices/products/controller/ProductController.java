package com.udemy.microservices.products.controller;

import com.udemy.microservices.products.entity.Product;
import com.udemy.microservices.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
//    private final Environment env;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/products")
    public List<Product> getAll() {
        return service.findAll().stream().map(product -> {
//            product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            product.setPort(port);
            return product;
        }).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable Long id) {
        Product product = service.findById(id);
//        product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        product.setPort(port);
        return product;
    }

}
