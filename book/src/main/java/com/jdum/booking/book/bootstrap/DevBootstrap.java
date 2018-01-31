package com.jdum.booking.book.bootstrap;

import com.jdum.booking.book.model.Inventory;
import com.jdum.booking.book.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author idumchykov
 * @since 1/31/18
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
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
