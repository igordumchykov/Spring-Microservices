package com.jdum.booking.book.controller;

import com.jdum.booking.book.model.BookingRecord;
import com.jdum.booking.book.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long book(@RequestBody BookingRecord record) {
        log.debug("Create booking: {} ", record);
        return bookingService.book(record);
    }

    @RequestMapping("/get/{id}")
    public BookingRecord getBooking(@PathVariable long id) {
        log.debug("Get booking for id: {} ", id);
        return bookingService.getBooking(id);
    }
}
