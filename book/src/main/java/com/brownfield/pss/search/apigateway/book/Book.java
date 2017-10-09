package com.brownfield.pss.search.apigateway.book;

import com.brownfield.pss.search.apigateway.book.model.Inventory;
import com.brownfield.pss.search.apigateway.book.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Import(AppConfig.class)
public class Book implements CommandLineRunner {

    @Autowired
    private HealthIndicator bookingHealthIndicator;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Book.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        initDB();
        doHealthCheck();
    }

    private void initDB() {
        inventoryRepository.save(newArrayList(
                new Inventory("BF100", "22-JAN-16", 100),
                new Inventory("BF101", "22-JAN-16", 100),
                new Inventory("BF102", "22-JAN-16", 100),
                new Inventory("BF103", "22-JAN-16", 100),
                new Inventory("BF104", "22-JAN-16", 100),
                new Inventory("BF105", "22-JAN-16", 100),
                new Inventory("BF106", "22-JAN-16", 100)
        ));
    }

    private void doHealthCheck() {
        Health health = bookingHealthIndicator.health();
        if (health.getStatus().equals(Status.DOWN)) {
            applicationContext.close();
        }
    }

}
