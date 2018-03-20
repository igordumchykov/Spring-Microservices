package com.jdum.booking.search;

import com.jdum.booking.search.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.jdum.booking"})
@EnableDiscoveryClient
@Import(AppConfig.class)
public class Search {

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(Search.class, args);
    }

    @PostConstruct
    public void setUp(){
        List<String> services = discoveryClient.getServices();
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        System.out.println(localServiceInstance);
        System.out.println(discoveryClient.description());
        System.out.println(services);
    }

}
