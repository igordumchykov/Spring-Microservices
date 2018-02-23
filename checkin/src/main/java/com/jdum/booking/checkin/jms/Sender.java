package com.jdum.booking.checkin.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jdum.booking.checkin.constants.Constants.CHECK_IN_QUEUE;

@Component
@RequiredArgsConstructor
public class Sender {

    @Autowired
    private RabbitMessagingTemplate template;

    public void send(Object message) {
        template.convertAndSend(CHECK_IN_QUEUE, message);
    }
}