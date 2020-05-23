package com.udemy.microservices.items.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.udemy.microservices.items.model.Item;
import com.udemy.microservices.items.model.Product;
import com.udemy.microservices.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService service;

    @Value("${custom.text}")
    private String configText;

    @GetMapping("/items")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(service.findById(id, quantity));
    }

    public ResponseEntity<?> alternativeMethod(Long id, Integer quantity) {
        Product p = new Product();
        p.setId(id);
        p.setName("PlayStation 4");
        p.setPrice(1150.00);
        return ResponseEntity.ok(new Item(p,quantity));
    }

    @GetMapping("config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {

        log.info(String.format("## Custom config text: %s",configText));

        Map<String, String> response = new HashMap<>();
        response.put("text",configText);
        response.put("port",port);
        return ResponseEntity.ok(response);
    }


}
