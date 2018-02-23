package com.jdum.booking.webface.web;

import com.google.common.collect.Iterables;
import com.jdum.booking.common.dto.BookingRecordDTO;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import com.jdum.booking.common.dto.PassengerDTO;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.webface.client.BookClient;
import com.jdum.booking.webface.client.CheckinClient;
import com.jdum.booking.webface.client.SearchClient;
import com.jdum.booking.webface.dto.UIData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static com.jdum.booking.webface.constants.Constants.*;
import static com.jdum.booking.webface.constants.REST.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebfaceController {

    @Autowired
    private CheckinClient checkinClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private BookClient bookClient;

    //Handles error using circuit breaker
//    @HystrixCommand(fallbackMethod = "getError")
    @PostMapping(TRIP_SEARCH_PATH)
    public String searchTrip(@ModelAttribute(UIDATA_ATTRIBUTE) UIData uiData, Model model) {

        List<TripDTO> trips = searchClient.getTrips(uiData.getSearchQuery());
        uiData.setTrips(trips);
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return SEARCH_TRIP_RESULT_VIEW;
    }

    @PostMapping(BOOKING_BOOK_PATH)
    public String book(@ModelAttribute(TRIP_ATTRIBUTE) TripDTO trip, Model model) {
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(trip, new PassengerDTO()));
        return BOOKING_INPUT_VIEW;
    }

    @PostMapping(BOOKING_CONFIRM_PATH)
    public String confirmBooking(@ModelAttribute(UIDATA_ATTRIBUTE) UIData uiData, Model model) {

        TripDTO trip = uiData.getSelectedTrip();
        BookingRecordDTO booking = new BookingRecordDTO(trip);

        PassengerDTO passenger = uiData.getPassenger();
        passenger.setBookingRecord(booking);
        booking.setPassengers(newHashSet(passenger));

        Long bookingId = bookClient.create(booking);

        model.addAttribute(MESSAGE_ATTRIBUTE, BOOKING_CONFIRMED_MSG + bookingId);
        return BOOKING_CONFIRMATION_VIEW;
    }

    @GetMapping(BOOKING_GET_PATH)
    public String searchBooking(Model model) {

        UIData UIData = new UIData();
        UIData.setBookingId(BOOK_ID_DISPLAY_DEFAULT);//will be displayed
        model.addAttribute(UIDATA_ATTRIBUTE, UIData);

        return BOOKING_DETAILS_VIEW;
    }

    @PostMapping(BOOKING_GET_DETAILS_PATH)
    public String getBookingDetails(@ModelAttribute(UIDATA_ATTRIBUTE) UIData uiData, Model model) {

        Long id = Long.parseLong(uiData.getBookingId());
        BookingRecordDTO booking = bookClient.getBookingRecord(id);
        TripDTO trip = new TripDTO(booking);

        PassengerDTO passenger = Iterables.getFirst(booking.getPassengers(), null);
        uiData.setPassenger(passenger);
        uiData.setSelectedTrip(trip);
        uiData.setBookingId(String.valueOf(id));
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return BOOKING_DETAILS_VIEW;
    }

    @PostMapping(BOOKING_CHECK_IN_PATH)
    public String checkInBookingRecord(@ModelAttribute(CHECK_IN_ATTRIBUTE) CheckInRecordDTO checkIn, Model model) {

        checkIn.setSeatNumber(SEAT_NUMBER);// TODO: 2/14/18 add logic to generate seat number automatically
        Long checkInId = checkinClient.create(checkIn);
        model.addAttribute(MESSAGE_ATTRIBUTE, BOOKING_CHECK_IN_MSG + checkInId);

        return CHECK_IN_CONFIRM_VIEW;
    }
}