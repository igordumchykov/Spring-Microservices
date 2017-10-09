package com.brownfield.pss.search.apigateway.client.healthcheck;

import com.brownfield.pss.search.apigateway.client.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.google.common.collect.Sets.newHashSet;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Slf4j
@Component
public class WebfaceHealthIndicator implements HealthIndicator {

    @Autowired
    private RestTemplate searchClient;

    @Autowired
    private RestTemplate bookingClient;

    @Autowired
    private RestTemplate checkInClient;

    @Value("${application.webclient.checkin.create.url}")
    private String checkinCreateUrl;

    @Value("${application.webclient.search.get.url}")
    private String searchGetUrl;

    @Value("${application.webclient.booking.create.url}")
    private String bookingCreateUrl;

    @Override
    public Health health() {
        log.debug("Calling health check for webface service");
        try {

        SearchQuery searchQuery = new SearchQuery("NYC", "SFO", "22-JAN-16");

        Flight[] flights = searchClient.postForObject(searchGetUrl, searchQuery, Flight[].class);

        Arrays.asList(flights).forEach(flight -> log.info("Flight: {}", flight));

        //create a booking only if there are flights.
//        if (flights.length == 0) {
//            return;
//        }

        Flight flight = flights[0];
        BookingRecord booking = new BookingRecord(flight.getFlightNumber(), flight.getOrigin(),
                flight.getDestination(), flight.getFlightDate(), null,
                flight.getFares().getFare());

        booking.setPassengers(newHashSet(
                new Passenger("Gavin", "Franc", "Male", booking)
        ));

        long bookingId;
        try {
            bookingId = bookingClient.postForObject(bookingCreateUrl, booking, long.class);
            log.debug("Booking created: {}", bookingId);
        } catch (Exception e) {
            log.error("BOOKING SERVICE NOT AVAILABLE...!!!");
            return Health.down(e).build();
        }

        //check in passenger
//        if (bookingId == 0)
//            return;

        try {
            CheckInRecord checkIn = new CheckInRecord("Franc", "Gavin", "28C", null, "BF101", "22-JAN-16", bookingId);
            long checkinId = checkInClient.postForObject(checkinCreateUrl, checkIn, long.class);
            log.debug("Checked IN: {}", checkinId);
        } catch (Exception e) {
            log.error("CHECK IN SERVICE NOT AVAILABLE...!!!");
            return Health.down(e).build();
        }

            log.info("Webface service is UP");
            return Health.up().build();
        } catch (Exception e) {
            log.error("Webface service is DOWN");
            return Health.down(e).build();
        }
    }
}
