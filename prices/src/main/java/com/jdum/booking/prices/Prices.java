package com.jdum.booking.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.jdum.booking"})
@EnableDiscoveryClient
public class Prices {

    public static void main(String[] args) {
        SpringApplication.run(Prices.class, args);
    }
}
