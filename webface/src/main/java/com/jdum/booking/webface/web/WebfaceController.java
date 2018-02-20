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

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebfaceController {

    static final String UIDATA_ATTRIBUTE = "uiData";
    static final String TRIP_ATTRIBUTE = "trip";
    static final String CHECK_IN_ATTRIBUTE = "checkIn";

    static final String SEARCH_TRIP_RESULT_VIEW = "searchTripResult";
    static final String BOOKING_INPUT_VIEW = "bookInput";
    static final String BOOKING_CONFIRMATION_VIEW = "bookingConfirmation";
    static final String BOOKING_DETAILS_VIEW = "bookingDetails";
    static final String CHECK_IN_CONFIRM_VIEW = "checkinConfirm";
    static final String MESSAGE_ATTRIBUTE = "message";

    @Autowired
    private CheckinClient checkinClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private BookClient bookClient;

    //Handles error using circuit breaker
//    @HystrixCommand(fallbackMethod = "getError")
    @PostMapping("/trip/search")
    public String searchTrip(@ModelAttribute(UIDATA_ATTRIBUTE) UIData uiData, Model model) {

        List<TripDTO> trips = searchClient.getTrips(uiData.getSearchQuery());
        uiData.setTrips(trips);
        model.addAttribute(UIDATA_ATTRIBUTE, uiData);

        return SEARCH_TRIP_RESULT_VIEW;
    }

    @PostMapping("/booking/book")
    public String book(@ModelAttribute(TRIP_ATTRIBUTE) TripDTO trip, Model model) {
        model.addAttribute(UIDATA_ATTRIBUTE, new UIData(trip, new PassengerDTO()));
        return BOOKING_INPUT_VIEW;
    }

    @PostMapping("/booking/confirm")
    public String confirmBooking(@ModelAttribute(UIDATA_ATTRIBUTE) UIData uiData, Model model) {

        TripDTO trip = uiData.getSelectedTrip();
        BookingRecordDTO booking = new BookingRecordDTO(trip);

        PassengerDTO passenger = uiData.getPassenger();
        passenger.setBookingRecord(booking);
        booking.setPassengers(newHashSet(passenger));

        Long bookingId = bookClient.create(booking);

        model.addAttribute(MESSAGE_ATTRIBUTE, "Your Booking is confirmed. Reference Number is " + bookingId);
        return BOOKING_CONFIRMATION_VIEW;
    }

    @GetMapping("/booking/get")
    public String searchBooking(Model model) {

        UIData UIData = new UIData();
        UIData.setBookingId("5");//will be displayed
        model.addAttribute(UIDATA_ATTRIBUTE, UIData);

        return BOOKING_DETAILS_VIEW;
    }

    @PostMapping("/booking/get/details")
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

    @PostMapping("/booking/checkIn")
    public String checkInBookingRecord(@ModelAttribute(CHECK_IN_ATTRIBUTE) CheckInRecordDTO checkIn, Model model) {

        checkIn.setSeatNumber("28C");// TODO: 2/14/18 add logic to generate seat number automatically
        Long checkInId = checkinClient.create(checkIn);
        model.addAttribute(MESSAGE_ATTRIBUTE, "Checked In, Seat Number is 28c , check in id is " + checkInId);

        return CHECK_IN_CONFIRM_VIEW;
    }
}