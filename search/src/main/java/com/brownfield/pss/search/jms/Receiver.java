package com.brownfield.pss.search.jms;

import com.brownfield.pss.search.service.SearchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Receiver {

    @Autowired
    private SearchServiceImpl searchComponent;

    @Bean
    public Queue queue() {
        return new Queue("SearchQ", false);
    }

    @RabbitListener(queues = "SearchQ")
    public void processMessage(Map<String, Object> fare) {
        log.debug("Fare received: {}", fare);
        searchComponent.updateInventory((String) fare.get("FLIGHT_NUMBER"), (String) fare.get("FLIGHT_DATE"), (int) fare.get("NEW_INVENTORY"));
        //call repository and update the fare for the given flight
    }
}