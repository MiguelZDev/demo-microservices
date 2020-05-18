package com.udemy.microservices.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name = "asd")
@EnableFeignClients
@SpringBootApplication
public class ItemsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemsServiceApplication.class, args);
    }

}
