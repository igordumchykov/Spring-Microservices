package com.jdum.booking.checkin.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jdum.booking.checkin.constants.Constants.CHECK_IN_QUEUE;

/**
 * @author idumchykov
 * @since 2/8/18
 */
@Configuration
public class AppConfig {

    @Bean
    public Queue checkinQueue() {
        return new Queue(CHECK_IN_QUEUE, false);
    }
}
