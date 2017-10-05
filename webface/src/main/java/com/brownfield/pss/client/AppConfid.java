package com.brownfield.pss.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Configuration
public class AppConfid {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
