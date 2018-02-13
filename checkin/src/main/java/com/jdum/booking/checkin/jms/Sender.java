package com.jdum.booking.checkin.jms;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Sender {

    @Autowired
    private RabbitMessagingTemplate template;

    public void send(Object message) {
        template.convertAndSend("CheckINQ", message);
    }
}