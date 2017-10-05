package com.brownfield.pss.book.jms;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Sender {

    @Autowired
    private RabbitMessagingTemplate template;

    @Bean
    public Queue queue() {
        return new Queue("SearchQ", false);
    }

    @Bean
    public Queue queue1() {
        return new Queue("CheckINQ", false);
    }


    public void send(Object message) {
        log.debug("Sending a booking event: {}", message);
        template.convertAndSend("SearchQ", message);
    }
}