package com.jdum.booking.search.config;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
