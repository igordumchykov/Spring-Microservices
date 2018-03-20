package com.jdum.booking.search.bootstrap;

import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.model.Price;
import com.jdum.booking.search.model.Trip;
import com.jdum.booking.search.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author idumchykov
 * @since 1/31/18
 */
@Component
//@Profile({"dev"})
public class DevBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    private static boolean eventReceived;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {
        initDB();
    }

    private void initDB() {

        if (eventReceived)
            return;

        eventReceived = true;

        tripRepository.save(newArrayList(
                new Trip("BF100", "SEA", "SFO", "22-JAN-16", new Price("100", "USD"), new Inventory(100)),
                new Trip("BF101", "NYC", "SFO", "22-JAN-16", new Price("101", "USD"), new Inventory(100)),
                new Trip("BF102", "CHI", "SFO", "22-JAN-16", new Price("102", "USD"), new Inventory(100)),
                new Trip("BF103", "HOU", "SFO", "22-JAN-16", new Price("103", "USD"), new Inventory(100)),
                new Trip("BF104", "LAX", "SFO", "22-JAN-16", new Price("104", "USD"), new Inventory(100)),
                new Trip("BF105", "NYC", "SFO", "22-JAN-16", new Price("105", "USD"), new Inventory(100)),
                new Trip("BF106", "NYC", "SFO", "22-JAN-16", new Price("106", "USD"), new Inventory(100))
        ));
    }
}
