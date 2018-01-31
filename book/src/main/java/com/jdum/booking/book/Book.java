package com.jdum.booking.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableFeignClients
public class Book {

    public static void main(String[] args) {
        SpringApplication.run(Book.class, args);
    }
}
