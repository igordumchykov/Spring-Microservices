package com.jdum.booking.book.jms;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Sender {

    @Autowired
    private RabbitMessagingTemplate template;

    public void send(Object message) {
        log.debug("Sending a booking event: {}", message);
        template.convertAndSend("SearchQ", message);
    }
}