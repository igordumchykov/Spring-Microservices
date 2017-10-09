package com.brownfield.pss.search.apigateway.checkin.healthcheck;

import com.brownfield.pss.search.apigateway.checkin.model.CheckInRecord;
import com.brownfield.pss.search.apigateway.checkin.repository.CheckinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Slf4j
@Component
public class CheckinHealthIndicator implements HealthIndicator {

    @Autowired
    private CheckinRepository checkinRepository;

    @Override
    public Health health() {
        log.debug("Calling health check for checkin service");
        try {
            CheckInRecord record = new CheckInRecord("Igor", "Dumchykov", "28A", new Date(), "BF101", "22-JAN-16", 1);
            CheckInRecord result = checkinRepository.save(record);
            log.debug("checked in successfully: {}", result);
            log.debug("Looking to load checkedIn record: {}", checkinRepository.findOne(result.getId()));
            log.info("Check service is UP");
            return Health.up().build();
        } catch (Exception e){
            log.error("Check service is DOWN");
            return Health.down(e).build();
        }
    }
}
