package com.jdum.booking.webface.controller;

import com.jdum.booking.webface.client.BookClient;
import com.jdum.booking.webface.client.CheckinClient;
import com.jdum.booking.webface.client.SearchClient;
import com.jdum.booking.webface.dto.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class WebfaceController {

    @Autowired
    private CheckinClient checkinClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private BookClient bookClient;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        SearchQuery query = new SearchQuery("NYC", "SFO", "22-JAN-16");
        UIData uiData = new UIData();
        uiData.setSearchQuery(query);
        model.addAttribute("uidata", uiData);
        return "search";
    }

    @HystrixCommand(fallbackMethod = "getError")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute UIData uiData, Model model) {
        List<Flight> flights = searchClient.getFlights(uiData.getSearchQuery());
        uiData.setFlights(flights);
        model.addAttribute("uidata", uiData);
        return "result";
    }

    public String getError(UIData uiData, Model model){
        uiData.setFlights(Collections.emptyList());
        model.addAttribute("uidata", uiData);
        return "result";
    }

    @RequestMapping(value = "/book/{flightNumber}/{origin}/{destination}/{flightDate}/{fare}", method = RequestMethod.GET)
    public String bookQuery(@PathVariable String flightNumber,
                            @PathVariable String origin,
                            @PathVariable String destination,
                            @PathVariable String flightDate,
                            @PathVariable String fare,
                            Model model) {
        UIData uiData = new UIData();
        Flight flight = new Flight(flightNumber, origin, destination, flightDate, new Fares(fare, "AED"));
        uiData.setSelectedFlight(flight);
        uiData.setPassenger(new Passenger());
        model.addAttribute("uidata", uiData);
        return "book";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String ConfirmBooking(@ModelAttribute UIData uiData, Model model) {
        Flight flight = uiData.getSelectedFlight();
        BookingRecord booking = new BookingRecord(flight.getFlightNumber(), flight.getOrigin(),
                flight.getDestination(), flight.getFlightDate(), null,
                flight.getFares().getFare());
        Set<Passenger> passengers = new HashSet<>();
        Passenger pax = uiData.getPassenger();
        pax.setBookingRecord(booking);
        passengers.add(uiData.getPassenger());
        booking.setPassengers(passengers);
        long bookingId = bookClient.create(booking);
        model.addAttribute("message", "Your Booking is confirmed. Reference Number is " + bookingId);
        return "confirm";
    }

    @RequestMapping(value = "/search-booking", method = RequestMethod.GET)
    public String searchBookingForm(Model model) {
        UIData uiData = new UIData();
        uiData.setBookingid("5");
        model.addAttribute("uidata", uiData);
        return "bookingsearch";
    }

    @RequestMapping(value = "/search-booking-get", method = RequestMethod.POST)
    public String searchBookingSubmit(@ModelAttribute UIData uiData, Model model) {
        long id = Long.parseLong(uiData.getBookingid());
        BookingRecord booking = bookClient.getBookingRecord(id);
        Flight flight = new Flight(booking.getFlightNumber(), booking.getOrigin(), booking.getDestination()
                , booking.getFlightDate(), new Fares(booking.getFare(), "AED"));
        Passenger pax = booking.getPassengers().iterator().next();
        Passenger paxUI = new Passenger(pax.getFirstName(), pax.getLastName(), pax.getGender(), null);
        uiData.setPassenger(paxUI);
        uiData.setSelectedFlight(flight);
        uiData.setBookingid(String.valueOf(id));
        model.addAttribute("uidata", uiData);
        return "bookingsearch";
    }

    @RequestMapping(value = "/checkin/{flightNumber}/{origin}/{destination}/{flightDate}/{fare}/{firstName}/{lastName}/{gender}/{bookingid}", method = RequestMethod.GET)
    public String bookQuery(@PathVariable String flightNumber,
                            @PathVariable String origin,
                            @PathVariable String destination,
                            @PathVariable String flightDate,
                            @PathVariable String fare,
                            @PathVariable String firstName,
                            @PathVariable String lastName,
                            @PathVariable String gender,
                            @PathVariable String bookingid,
                            Model model) {


        CheckInRecord checkIn = new CheckInRecord(firstName, lastName, "28C", null,
                flightDate, flightDate, new Long(bookingid));

        long checkinId = checkinClient.create(checkIn);
        model.addAttribute("message", "Checked In, Seat Number is 28c , checkin id is " + checkinId);
        return "checkinconfirm";
    }
}