package com.jdum.booking.checkin.jms;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Sender {

    @Autowired
    private RabbitMessagingTemplate template;

    @Bean
    public Queue queue() {
        return new Queue("CheckINQ", false);
    }

    public void send(Object message) {
        template.convertAndSend("CheckINQ", message);
    }
}