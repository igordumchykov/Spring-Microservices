package com.brownfield.pss.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

@EnableGlobalMethodSecurity
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@Import(AppConfid.class)
public class Website implements CommandLineRunner {

    private RestTemplate searchClient = new RestTemplate();

    private RestTemplate bookingClient = new RestTemplate();

    private RestTemplate checkInClient = new RestTemplate();

    private RestTemplate restClient = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(Website.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        //Search for a flight
//        SearchQuery searchQuery = new SearchQuery("NYC", "SFO", "22-JAN-16");
//
//        Flight[] flights = searchClient.postForObject("http://localhost:8090/search/get", searchQuery, Flight[].class);
//
//        Arrays.asList(flights).forEach(flight -> log.info(" flight >" + flight));
//
//        //create a booking only if there are flights.
//        if (flights.length == 0) {
//            return;
//        }
//        Flight flight = flights[0];
//        BookingRecord booking = new BookingRecord(flight.getFlightNumber(), flight.getOrigin(),
//                flight.getDestination(), flight.getFlightDate(), null,
//                flight.getFares().getFare());
//        Set<Passenger> passengers = new HashSet<Passenger>();
//        passengers.add(new Passenger("Gavin", "Franc", "Male", booking));
//        booking.setPassengers(passengers);
//        long bookingId = 0;
//        try {
//            bookingId = bookingClient.postForObject("http://localhost:8060/booking/create", booking, long.class);
//            log.info("Booking created " + bookingId);
//        } catch (Exception e) {
//            log.error("BOOKING SERVICE NOT AVAILABLE...!!!");
//        }
//
//        //check in passenger
//        if (bookingId == 0)
//            return;
//
//        try {
//            CheckInRecord checkIn = new CheckInRecord("Franc", "Gavin", "28C", null, "BF101", "22-JAN-16", bookingId);
//            long checkinId = checkInClient.postForObject("http://localhost:8070/checkin/create", checkIn, long.class);
//            log.info("Checked IN " + checkinId);
//        } catch (Exception e) {
//            log.error("CHECK IN SERVICE NOT AVAILABLE...!!!");
//        }
    }

}