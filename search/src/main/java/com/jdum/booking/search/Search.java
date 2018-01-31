package com.jdum.booking.search;

import com.jdum.booking.search.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@Import(AppConfig.class)
public class Search {

    public static void main(String[] args) {
        SpringApplication.run(Search.class, args);
    }

}
