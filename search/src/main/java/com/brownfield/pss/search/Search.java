package com.brownfield.pss.search;

import com.brownfield.pss.search.model.Fares;
import com.brownfield.pss.search.model.Flight;
import com.brownfield.pss.search.model.Inventory;
import com.brownfield.pss.search.repository.FlightRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Search implements CommandLineRunner {

    //indicate that the span ID has to be created every time a call hits the service
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HealthIndicator searchHealthIndicator;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Search.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        initDB();
        doHealthCheck();
    }

    private void initDB() {
        flightRepository.save(newArrayList(
                new Flight("BF100", "SEA", "SFO", "22-JAN-16", new Fares("100", "USD"), new Inventory(100)),
                new Flight("BF101", "NYC", "SFO", "22-JAN-16", new Fares("101", "USD"), new Inventory(100)),
                new Flight("BF105", "NYC", "SFO", "22-JAN-16", new Fares("105", "USD"), new Inventory(100)),
                new Flight("BF106", "NYC", "SFO", "22-JAN-16", new Fares("106", "USD"), new Inventory(100)),
                new Flight("BF102", "CHI", "SFO", "22-JAN-16", new Fares("102", "USD"), new Inventory(100)),
                new Flight("BF103", "HOU", "SFO", "22-JAN-16", new Fares("103", "USD"), new Inventory(100)),
                new Flight("BF104", "LAX", "SFO", "22-JAN-16", new Fares("104", "USD"), new Inventory(100))
        ));
    }

    private void doHealthCheck() {
        Health health = searchHealthIndicator.health();
        if (health.getStatus().equals(Status.DOWN)) {
            applicationContext.close();
        }
    }

}
