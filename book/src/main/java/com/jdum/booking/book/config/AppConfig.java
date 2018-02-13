package com.jdum.booking.book.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author idumchykov
 * @since 2/8/18
 */
@Configuration
public class AppConfig {

    @Bean
    public Queue searchQueue() {
        return new Queue("SearchQ", false);
    }

    @Bean
    public Queue checkinQueue() {
        return new Queue("CheckINQ", false);
    }
}
