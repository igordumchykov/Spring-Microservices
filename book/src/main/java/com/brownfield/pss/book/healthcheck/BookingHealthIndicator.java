package com.brownfield.pss.book.healthcheck;

import com.brownfield.pss.book.model.BookingRecord;
import com.brownfield.pss.book.model.Passenger;
import com.brownfield.pss.book.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.google.common.collect.Sets.newHashSet;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Slf4j
@Component
public class BookingHealthIndicator implements HealthIndicator {

    @Autowired
    private BookingService bookingService;

    @Override
    public Health health() {
        log.debug("Calling health check for book service");
        try {
            BookingRecord booking = new BookingRecord("BF101", "NYC", "SFO", "22-JAN-16", new Date(), "101");
            booking.setPassengers(newHashSet(new Passenger("Igor", "Dumchykov", "Male", booking)));
            long record = bookingService.book(booking);
            log.info("Booking successfully saved: {}", record);
            log.info("Loaded booking: {}", bookingService.getBooking(record));
            log.info("Book service is UP");
            return Health.up().build();
        } catch (Exception e){
            log.error("Book service is DOWN");
            return Health.down(e).build();
        }
    }
}
