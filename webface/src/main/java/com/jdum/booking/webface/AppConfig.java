package com.jdum.booking.webface;

import org.springframework.context.annotation.Configuration;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Configuration
public class AppConfig {

    //is used instead of Feign client
    //use @Import(AppConfig.class) in main spring boot class to import configuration
//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
