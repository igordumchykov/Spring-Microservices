package com.jdum.booking.book;

import com.jdum.booking.book.model.Inventory;
import com.jdum.booking.book.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@EnableFeignClients
public class Book implements CommandLineRunner {

    @Autowired
    private InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(Book.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        initDB();
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


}
