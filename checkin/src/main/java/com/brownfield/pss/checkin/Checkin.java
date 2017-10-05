package com.brownfield.pss.checkin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class Checkin implements CommandLineRunner {

    @Autowired
    private HealthIndicator checkinHealthIndicator;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Checkin.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        doHealthCheck();
    }

    private void doHealthCheck() {
        Health health = checkinHealthIndicator.health();
        if (health.getStatus().equals(Status.DOWN)) {
            applicationContext.close();
        }
    }
}
