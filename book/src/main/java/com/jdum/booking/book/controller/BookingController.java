package com.jdum.booking.book.controller;

import com.jdum.booking.book.service.BookingService;
import com.jdum.booking.common.dto.BookingRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Long book(@RequestBody BookingRecordDTO bookingRecord) {
        log.debug("Create booking: {} ", bookingRecord);
        return bookingService.book(bookingRecord);
    }

    @RequestMapping("/get/{id}")
    public BookingRecordDTO getBooking(@PathVariable Long id) {
        log.debug("Get booking for id: {} ", id);
        return bookingService.getBooking(id);
    }
}
