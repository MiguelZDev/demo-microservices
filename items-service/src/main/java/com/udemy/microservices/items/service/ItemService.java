package com.udemy.microservices.items.service;

import com.udemy.microservices.items.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer quantity);

    // TODO: Implement using WebClient (reactive)
}
