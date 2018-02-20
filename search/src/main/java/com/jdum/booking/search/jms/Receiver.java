package com.jdum.booking.search.jms;

import com.jdum.booking.search.service.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class Receiver {

    private static String BUS_NUMBER_MSG = "BUS_NUMBER";
    private static String TRIP_DATE_MSG = "TRIP_DATE";
    private static String NEW_INVENTORY_MSG = "NEW_INVENTORY";

    @Autowired
    private SearchServiceImpl searchComponent;

    @Bean
    public Queue queue() {
        return new Queue("SearchQ", false);
    }

    @RabbitListener(queues = "SearchQ")
    public void processMessage(Map<String, Object> price) {
        log.debug("Price received: {}", price);
        //call repository and update the priceAmount for the given trip
        searchComponent.updateInventory((String) price.get(BUS_NUMBER_MSG),
                (String) price.get(TRIP_DATE_MSG), (int) price.get(NEW_INVENTORY_MSG));
    }
}