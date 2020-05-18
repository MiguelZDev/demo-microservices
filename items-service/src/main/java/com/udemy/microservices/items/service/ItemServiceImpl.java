package com.udemy.microservices.items.service;

import com.udemy.microservices.items.model.Item;
import com.udemy.microservices.items.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final RestTemplate restTemplate;

    @Value("${products-service.endpoint.url}")
    private String endpointUrl;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(restTemplate.getForObject(endpointUrl+"products", Product[].class));
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Product product = restTemplate.getForObject(endpointUrl+"products/{id}", Product.class, id);
        return new Item(product,quantity);
    }
}
