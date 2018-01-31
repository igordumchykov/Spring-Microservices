package com.jdum.booking.search.bootstrap;

import com.jdum.booking.search.model.Fares;
import com.jdum.booking.search.model.Flight;
import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.repository.FlightRepository;
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
    private FlightRepository flightRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initDB();
    }

    private void initDB() {
        flightRepository.save(newArrayList(
                new Flight("BF100", "SEA", "SFO", "22-JAN-16", new Fares("100", "USD"), new Inventory(100)),
                new Flight("BF101", "NYC", "SFO", "22-JAN-16", new Fares("101", "USD"), new Inventory(100)),
                new Flight("BF105", "NYC", "SFO", "22-JAN-16", new Fares("105", "USD"), new Inventory(100)),
                new Flight("BF106", "NYC", "SFO", "22-JAN-16", new Fares("106", "USD"), new Inventory(100)),
                new Flight("BF102", "CHI", "SFO", "22-JAN-16", new Fares("102", "USD"), new Inventory(100)),
                new Flight("BF103", "HOU", "SFO", "22-JAN-16", new Fares("103", "USD"), new Inventory(100)),
                new Flight("BF104", "LAX", "SFO", "22-JAN-16", new Fares("104", "USD"), new Inventory(100))
        ));
    }
}
