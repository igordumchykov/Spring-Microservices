package com.jdum.booking.checkin;

import com.jdum.booking.checkin.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.jdum.booking"})
@EnableDiscoveryClient
@Import(AppConfig.class)
public class Checkin {

    public static void main(String[] args) {
        SpringApplication.run(Checkin.class, args);
    }
}
