package com.jdum.booking.prices.bootstrap;

import com.jdum.booking.prices.model.Price;
import com.jdum.booking.prices.repository.PricesRepository;
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
    private PricesRepository pricesRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initDB();
    }

    private void initDB() {

        if(eventReceived)
            return;

        eventReceived = true;

        pricesRepository.save(newArrayList(
                new Price("BF100", "22-JAN-16", "100"),
                new Price("BF101", "22-JAN-16", "101"),
                new Price("BF102", "22-JAN-16", "102"),
                new Price("BF103", "22-JAN-16", "103"),
                new Price("BF104", "22-JAN-16", "104"),
                new Price("BF105", "22-JAN-16", "105"),
                new Price("BF106", "22-JAN-16", "106")
        ));
    }
}
