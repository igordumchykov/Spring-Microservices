package com.brownfield.pss.search.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class SearchApigateway {

    //indicate that the span ID has to be created every time a call hits the service
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplae(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SearchApigateway.class, args);
    }
}
