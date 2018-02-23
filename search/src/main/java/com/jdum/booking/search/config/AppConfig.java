package com.jdum.booking.search.config;

import org.springframework.amqp.core.Queue;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jdum.booking.search.constants.Constants.SEARCH_QUEUE;

/**
 * @author idumchykov
 * @since 1/31/18
 */
@Configuration
public class AppConfig {

    //indicate that the span ID has to be createdTime every time a call hits the service
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

    @Bean
    public Queue queue() {
        return new Queue(SEARCH_QUEUE, false);
    }
}
