package com.jdum.booking.search;

import com.jdum.booking.search.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.jdum.booking"})
@EnableDiscoveryClient
@Import(AppConfig.class)
public class Search {

    public static void main(String[] args) {
        SpringApplication.run(Search.class, args);
    }

}
