package com.jdum.booking.webface.controller;

import com.google.common.collect.Iterables;
import com.jdum.booking.common.dto.BookingRecordDTO;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import com.jdum.booking.common.dto.PassengerDTO;
import com.jdum.booking.common.dto.TripDTO;
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
    @RequestMapping(value = "/trip/search", method = RequestMethod.POST)
    public String searchTrip(@ModelAttribute UIData uiData, Model model) {

        List<TripDTO> trips = searchClient.getTrips(uiData.getSearchQuery());
        uiData.setTrips(trips);
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return "searchTripResult";
    }

    public String getError(UIData uiData, Model model) {
        uiData.setTrips(Collections.emptyList());
        model.addAttribute("uidata", uiData);
        return "searchTripResult";
    }

    @RequestMapping(value = "/booking/book", method = RequestMethod.POST)
    public String book(@ModelAttribute TripDTO trip, Model model) {
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(trip, new PassengerDTO()));
        return "bookInput";
    }

    @RequestMapping(value = "/booking/confirm", method = RequestMethod.POST)
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
        return "bookingConfirmation";
    }

    @RequestMapping(value = "/booking/get", method = RequestMethod.GET)
    public String searchBooking(Model model) {

        UIData uiData = new UIData();
        uiData.setBookingId("5");//will be displayed
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return "bookingDetails";
    }

    @RequestMapping(value = "/booking/get/details", method = RequestMethod.POST)
    public String getBooking(@ModelAttribute UIData uiData, Model model) {

        Long id = Long.parseLong(uiData.getBookingId());
        BookingRecordDTO booking = bookClient.getBookingRecord(id);
        TripDTO trip = new TripDTO(booking);

        PassengerDTO passenger = Iterables.getFirst(booking.getPassengers(), null);
        uiData.setPassenger(passenger);
        uiData.setSelectedTrip(trip);
        uiData.setBookingId(String.valueOf(id));
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return "bookingDetails";
    }

    @RequestMapping(value = "/booking/checkin", method = RequestMethod.POST)
    public String bookQuery(@ModelAttribute CheckInRecordDTO checkInRecordDTO, Model model) {

        checkInRecordDTO.setSeatNumber("28C");// TODO: 2/14/18 add logic to generate seat number automatically
        Long checkinId = checkinClient.create(checkInRecordDTO);
        model.addAttribute("message", "Checked In, Seat Number is 28c , checkin id is " + checkinId);

        return "checkinConfirm";
    }
}