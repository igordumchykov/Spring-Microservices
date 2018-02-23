package com.jdum.booking.search.jms;

import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.jdum.booking.search.constants.Constants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class Receiver {

    @Autowired
    private SearchService searchService;

    @RabbitListener(queues = SEARCH_QUEUE)
    public void processMessage(Map<String, Object> message) {

        log.debug("Received message {}", message);

        try {
            searchService.updateInventory(
                    (String) message.get(BUS_NUMBER_MSG),
                    (String) message.get(TRIP_DATE_MSG),
                    (int) message.get(NEW_INVENTORY_MSG));
        } catch (NotFoundException e) {
            log.error(e.getMessage());
        }
    }
}