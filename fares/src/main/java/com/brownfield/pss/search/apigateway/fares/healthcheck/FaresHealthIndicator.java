package com.brownfield.pss.search.apigateway.fares.healthcheck;

import com.brownfield.pss.search.apigateway.fares.repository.FaresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Slf4j
@Component
public class FaresHealthIndicator implements HealthIndicator {

    @Autowired
    private FaresRepository faresRepository;

    @Override
    public Health health() {
        log.debug("Calling health check for fares service");
        try {
            log.debug("Result: {}", faresRepository.getFareByFlightNumberAndFlightDate("BF101", "22-JAN-16"));
            log.info("Fares service is UP");
            return Health.up().build();
        } catch (Exception e) {
            log.error("Fares service is DOWN");
            return Health.down(e).build();
        }
    }
}
