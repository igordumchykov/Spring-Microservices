package com.jdum.booking.fares;

import com.jdum.booking.fares.model.Fare;
import com.jdum.booking.fares.repository.FaresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class Fares implements CommandLineRunner {

    @Autowired
    private FaresRepository faresRepository;

    public static void main(String[] args) {
        SpringApplication.run(Fares.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        initDB();
    }

    private void initDB() {
        faresRepository.save(newArrayList(
                new Fare("BF100", "22-JAN-16", "101"),
                new Fare("BF101", "22-JAN-16", "101"),
                new Fare("BF102", "22-JAN-16", "102"),
                new Fare("BF103", "22-JAN-16", "103"),
                new Fare("BF104", "22-JAN-16", "104"),
                new Fare("BF105", "22-JAN-16", "105"),
                new Fare("BF106", "22-JAN-16", "106")
        ));
    }

}
