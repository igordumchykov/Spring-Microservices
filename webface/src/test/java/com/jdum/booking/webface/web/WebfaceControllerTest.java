package com.jdum.booking.webface.web;

import com.jdum.booking.common.dto.*;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.webface.client.BookClient;
import com.jdum.booking.webface.client.CheckinClient;
import com.jdum.booking.webface.client.SearchClient;
import com.jdum.booking.webface.dto.UIData;
import com.jdum.booking.webface.exceptions.ExceptionHandlerControllerAdvice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.jdum.booking.webface.constants.Constants.*;
import static com.jdum.booking.webface.constants.REST.*;
import static java.util.Optional.ofNullable;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author idumchykov
 * @since 2/16/18
 */
@SuppressWarnings("FieldCanBeLocal")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {WebfaceController.class, ExceptionHandlerControllerAdvice.class})
public class WebfaceControllerTest {

    private static String FIRST_NAME = "TestName";
    private static String LAST_NAME = "TestSurname";
    private static String GENDER = "Male";
    private static String BUS_NUMBER = "BH100";
    private static String ORIGIN = "SFO";
    private static String DESTINATION = "NYC";
    private static String TRIP_DATE = "22-JAN-16";
    private static String PRICE_AMOUNT = "100";
    private static String CURRENCY = "USD";
    private static Long BOOK_ID = 1L;
    private static Long CHECK_IN_ID = 1L;

    @MockBean
    private SearchClient searchClient;

    @MockBean
    private BookClient bookClient;

    @MockBean
    private CheckinClient checkinClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTrips() throws Exception {
        List<TripDTO> trips = constructTrips();

        when(searchClient.getTrips(any(SearchQuery.class))).thenReturn(trips);

        mockMvc.perform(post(TRIP_SEARCH_PATH)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .flashAttr(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault())))
                .andExpect(status().isOk())
                .andExpect(view().name(SEARCH_TRIP_RESULT_VIEW))
                .andExpect(model().attribute(UIDATA_ATTRIBUTE,
                        new UIData(SearchQuery.getDefault())
                                .setTrips(trips)));
    }

    @Test
    public void shouldReturn404IfTripsNotFound() throws Exception {

        //noinspection unchecked
        when(searchClient.getTrips(any(SearchQuery.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(post(TRIP_SEARCH_PATH)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .flashAttr(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault())))
                .andExpect(status().isNotFound())
                .andExpect(view().name(NOT_FOUND_VIEW_NAME));
    }

    @Test
    public void shouldReturnBookingView() throws Exception {
        TripDTO trip = constructTrip();

        mockMvc.perform(post(BOOKING_BOOK_PATH)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .flashAttr(TRIP_ATTRIBUTE, trip))
                .andExpect(status().isOk())
                .andExpect(view().name(BOOKING_INPUT_VIEW))
                .andExpect(model().attribute(UIDATA_ATTRIBUTE, new UIData(trip, new PassengerDTO())));
    }

    @Test
    public void shouldCreateBookingWhenConfirm() throws Exception {
        TripDTO trip = constructTrip();

        when(bookClient.create(any(BookingRecordDTO.class))).thenReturn(BOOK_ID);

        mockMvc.perform(post(BOOKING_CONFIRM_PATH)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .flashAttr(UIDATA_ATTRIBUTE, new UIData(trip, new PassengerDTO())))
                .andExpect(status().isOk())
                .andExpect(view().name(BOOKING_CONFIRMATION_VIEW))
                .andExpect(model().attribute(MESSAGE_ATTRIBUTE, BOOKING_CONFIRMED_MSG + BOOK_ID));
    }

    @Test
    public void shouldReturnSearchBookingView() throws Exception {
        mockMvc.perform(get(BOOKING_GET_PATH))
                .andExpect(status().isOk())
                .andExpect(view().name(BOOKING_DETAILS_VIEW));
    }

    @Test
    public void shouldGetBookingDetails() throws Exception {
        BookingRecordDTO bookingRecordDTO = constructBookingDTO();

        when(bookClient.getBookingRecord(BOOK_ID)).thenReturn(bookingRecordDTO);

        mockMvc.perform(post(BOOKING_GET_DETAILS_PATH)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .flashAttr(UIDATA_ATTRIBUTE, new UIData()
                        .setBookingId(String.valueOf(BOOK_ID))))
                .andExpect(status().isOk())
                .andExpect(view().name(BOOKING_DETAILS_VIEW))
                .andExpect(model().attribute(UIDATA_ATTRIBUTE,
                        new UIData()
                                .setPassenger(constructPassenger(bookingRecordDTO))
                                .setSelectedTrip(constructTrip(bookingRecordDTO))
                                .setBookingId(String.valueOf(BOOK_ID))));
    }

    @Test
    public void bookQuery() throws Exception {
        CheckInRecordDTO checkInRecord = constructCheckInRecord();

        when(checkinClient.create(checkInRecord)).thenReturn(CHECK_IN_ID);

        mockMvc.perform(post(BOOKING_CHECK_IN_PATH)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .flashAttr(CHECK_IN_ATTRIBUTE, checkInRecord))
                .andExpect(status().isOk())
                .andExpect(view().name(CHECK_IN_CONFIRM_VIEW))
                .andExpect(model().attribute(MESSAGE_ATTRIBUTE, BOOKING_CHECK_IN_MSG + CHECK_IN_ID));
    }

    private List<TripDTO> constructTrips() {
        return newArrayList(constructTrip());
    }

    private TripDTO constructTrip() {

        return TripDTO.builder()
                .busNumber(BUS_NUMBER)
                .origin(ORIGIN)
                .destination(DESTINATION)
                .tripDate(TRIP_DATE)
                .price(PriceDTO.builder()
                        .busNumber(BUS_NUMBER)
                        .tripDate(TRIP_DATE)
                        .priceAmount(PRICE_AMOUNT)
                        .currency(CURRENCY)
                        .build())
                .build();
    }

    private BookingRecordDTO constructBookingDTO() {

        BookingRecordDTO bookingRecordDTO = new BookingRecordDTO()
                .setBusNumber(BUS_NUMBER)
                .setOrigin(ORIGIN)
                .setDestination(DESTINATION)
                .setBookingDate(new Date())
                .setPrice(PRICE_AMOUNT);

        PassengerDTO passenger = constructPassenger(bookingRecordDTO);
        bookingRecordDTO.setPassengers(newHashSet(passenger));

        return bookingRecordDTO;
    }

    private PassengerDTO constructPassenger(BookingRecordDTO bookingRecordDTO) {

        PassengerDTO passenger = new PassengerDTO()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER);

        ofNullable(bookingRecordDTO).ifPresent(booking -> passenger.setBookingRecord(bookingRecordDTO));

        return passenger;
    }

    private TripDTO constructTrip(BookingRecordDTO bookingRecordDTO) {
        return new TripDTO(bookingRecordDTO);
    }

    private CheckInRecordDTO constructCheckInRecord() {
        return new CheckInRecordDTO()
                .setSeatNumber("28C")
                .setBookingId(BOOK_ID)
                .setBusNumber(BUS_NUMBER)
                .setCheckInTime(new Date())
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setTripDate(TRIP_DATE);
    }

}