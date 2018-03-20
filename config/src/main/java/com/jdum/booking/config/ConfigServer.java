package com.jdum.booking.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }
}

@RestController
class Controller {

    @Value("${config.message}")
    private String message;

    @GetMapping("/message")
    public String get() {
        System.out.println(message);
        return message;
    }
}

