package com.jdum.booking.webface.controller;

import com.google.common.collect.Iterables;
import com.jdum.booking.common.dto.*;
import com.jdum.booking.webface.client.BookClient;
import com.jdum.booking.webface.client.CheckinClient;
import com.jdum.booking.webface.client.SearchClient;
import com.jdum.booking.webface.dto.UIData;
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

    private static final String UIDATA_ATTRIBUTE = "uidata";

    @Autowired
    private CheckinClient checkinClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private BookClient bookClient;

    @HystrixCommand(fallbackMethod = "getError")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute UIData uiData, Model model) {

        List<TripDTO> trips = searchClient.getTrips(uiData.getSearchQuery());
        uiData.setTrips(trips);
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return "result";
    }

    public String getError(UIData uiData, Model model) {
        uiData.setTrips(Collections.emptyList());
        model.addAttribute("uidata", uiData);
        return "result";
    }

    @RequestMapping(value = "/book/{busNumber}/{origin}/{destination}/{tripDate}/{price}", method = RequestMethod.GET)
    public String book(@PathVariable String busNumber,
                       @PathVariable String origin,
                       @PathVariable String destination,
                       @PathVariable String tripDate,
                       @PathVariable String price,
                       Model model) {

        TripDTO trip = new TripDTO(busNumber, origin, destination, tripDate, new PriceDTO(price, "AED"));
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(trip, new PassengerDTO()));

        return "book";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirmBooking(@ModelAttribute UIData uiData, Model model) {

        TripDTO trip = uiData.getSelectedTrip();
        BookingRecordDTO booking = new BookingRecordDTO(trip);

        Set<PassengerDTO> passengers = new HashSet<>();
        PassengerDTO passenger = uiData.getPassenger();
        passenger.setBookingRecord(booking);
        passengers.add(passenger);
        booking.setPassengers(passengers);

        Long bookingId = bookClient.create(booking);

        model.addAttribute("message", "Your Booking is confirmed. Reference Number is " + bookingId);
        return "confirm";
    }

    @RequestMapping(value = "/search-booking", method = RequestMethod.GET)
    public String searchBookingForm(Model model) {

        UIData uiData = new UIData();
        uiData.setBookingId("5");//will be displayed
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return "bookingsearch";
    }

    @RequestMapping(value = "/search-booking-get", method = RequestMethod.POST)
    public String searchBookingSubmit(@ModelAttribute UIData uiData, Model model) {

        Long id = Long.parseLong(uiData.getBookingId());
        BookingRecordDTO booking = bookClient.getBookingRecord(id);
        TripDTO trip = new TripDTO(booking);

        PassengerDTO passenger = Iterables.getFirst(booking.getPassengers(), null);
        uiData.setPassenger(passenger);
        uiData.setSelectedTrip(trip);
        uiData.setBookingId(String.valueOf(id));
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return "bookingsearch";
    }

    @RequestMapping(value = "/checkin/{busNumber}/{origin}/{destination}/{tripDate}/{price}/{firstName}/{lastName}/{gender}/{bookingId}", method = RequestMethod.GET)
    public String bookQuery(@PathVariable String busNumber,
                            @PathVariable String origin,
                            @PathVariable String destination,
                            @PathVariable String tripDate,
                            @PathVariable String price,
                            @PathVariable String firstName,
                            @PathVariable String lastName,
                            @PathVariable String gender,
                            @PathVariable String bookingId,
                            Model model) {

        CheckInRecordDTO checkIn = new CheckInRecordDTO(firstName, lastName, "28C", null,
                busNumber, tripDate, new Long(bookingId));

        Long checkinId = checkinClient.create(checkIn);
        model.addAttribute("message", "Checked In, Seat Number is 28c , checkin id is " + checkinId);

        return "checkinconfirm";
    }
}