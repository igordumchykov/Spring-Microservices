package com.brownfield.pss.search.apigateway.book.controller;

import com.brownfield.pss.search.apigateway.book.model.BookingRecord;
import com.brownfield.pss.search.apigateway.book.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/booking")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long book(@RequestBody BookingRecord record) {
        log.debug("Booking Request: {} ", record);
        return bookingService.book(record);
    }

    @RequestMapping("/get/{id}")
    public BookingRecord getBooking(@PathVariable long id) {
        return bookingService.getBooking(id);
    }
}
