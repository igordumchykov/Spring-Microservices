package com.jdum.booking.checkin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdum.booking.checkin.service.CheckinService;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jdum.booking.checkin.util.TestDataCreator.*;
import static com.jdum.booking.checkin.constants.REST.CHECK_IN_CREATE_PATH;
import static com.jdum.booking.checkin.constants.REST.CHECK_IN_GET_PATH;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CheckInController.class)
public class CheckInControllerTest {

    @MockBean
    private CheckinService checkinService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnCheckIn() throws Exception {
        CheckInRecordDTO checkInRecordDTO = constructCheckinRecordDTO();

        when(checkinService.getCheckInRecord(CHECK_IN_ID)).thenReturn(checkInRecordDTO);

        mockMvc.perform(get(CHECK_IN_GET_PATH, BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.busNumber", is(checkInRecordDTO.getBusNumber())))
                .andExpect(jsonPath("$.bookingId", is(checkInRecordDTO.getBookingId().intValue())))
                .andExpect(jsonPath("$.firstName", is(checkInRecordDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(checkInRecordDTO.getLastName())))
                .andExpect(jsonPath("$.checkInTime", is(checkInRecordDTO.getCheckInTime())))
                .andExpect(jsonPath("$.seatNumber", is(checkInRecordDTO.getSeatNumber())))
                .andExpect(jsonPath("$.tripDate", is(checkInRecordDTO.getTripDate())));
    }

    @Test
    public void shouldReturn404WhenGetCheckIn() throws Exception {

        doThrow(NotFoundException.class).when(checkinService).getCheckInRecord(CHECK_IN_ID);

        mockMvc.perform(get(CHECK_IN_GET_PATH, BOOK_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCheckIn() throws Exception {
        CheckInRecordDTO checkInRecordDTO = constructCheckinRecordDTO();

        when(checkinService.checkIn(checkInRecordDTO)).thenReturn(CHECK_IN_ID);

        mockMvc.perform(post(CHECK_IN_CREATE_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(checkInRecordDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(CHECK_IN_ID.toString()));
    }
}