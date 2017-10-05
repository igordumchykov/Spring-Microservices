package com.brownfield.pss.search.healthcheck;

import com.brownfield.pss.search.model.Flight;
import com.brownfield.pss.search.repository.FlightRepository;
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
public class SearchHealthIndicator implements HealthIndicator {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Health health() {
        log.debug("Calling health check for search service");
        try {
            log.info("Load flights: ");
            for (Flight flight : flightRepository.findByOriginAndDestinationAndFlightDate("NYC", "SFO", "22-JAN-16")) {
                log.debug(flight.toString());
            }
            log.info("Search service is UP");
            return Health.up().build();
        } catch (Exception e) {
            log.error("Search service is DOWN");
            return Health.down(e).build();
        }
    }
}
