package com.brownfield.pss.search.apigateway.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@Import(AppConfid.class)
public class Website implements CommandLineRunner {

    @Autowired
    private HealthIndicator webfaceHealthIndicator;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Website.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        doHealthCheck();
    }

    private void doHealthCheck() {
        Health health = webfaceHealthIndicator.health();
        if (health.getStatus().equals(Status.DOWN)) {
            applicationContext.close();
        }
    }
}