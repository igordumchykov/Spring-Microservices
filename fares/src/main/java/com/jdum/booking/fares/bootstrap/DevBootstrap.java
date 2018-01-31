package com.jdum.booking.fares.bootstrap;

import com.jdum.booking.fares.model.Fare;
import com.jdum.booking.fares.repository.FaresRepository;
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
    private FaresRepository faresRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
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
