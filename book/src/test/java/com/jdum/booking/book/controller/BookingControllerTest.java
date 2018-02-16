package com.jdum.booking.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdum.booking.book.service.BookingService;
import com.jdum.booking.common.dto.BookingRecordDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author idumchykov
 * @since 2/16/18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    private static Long BOOK_ID = 1L;
    private static String BUS_NUMBER = "BH100";

    private BookingRecordDTO bookingRecordDTO;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        bookingRecordDTO = constructDTO();
    }

    @Test
    public void shouldReturnIdWhenBook() throws Exception {
        when(bookingService.book(any(BookingRecordDTO.class))).thenReturn(BOOK_ID);

        mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(bookingRecordDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(BOOK_ID.toString()));
    }

    @Test
    public void getBooking() throws Exception {
        when(bookingService.getBooking(BOOK_ID)).thenReturn(bookingRecordDTO);

        mockMvc.perform(get("/get/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookingRecordDTO)));
    }

    private BookingRecordDTO constructDTO() {
        return new BookingRecordDTO()
                .setBusNumber(BUS_NUMBER);
    }

}