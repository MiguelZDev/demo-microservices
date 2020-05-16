package com.udemy.microservices.items.controller;

import com.udemy.microservices.items.model.Item;
import com.udemy.microservices.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @GetMapping("/items")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id, @RequestParam Integer quantity) {
        return new ResponseEntity<>(service.findById(id, quantity), HttpStatus.OK);
    }


}
