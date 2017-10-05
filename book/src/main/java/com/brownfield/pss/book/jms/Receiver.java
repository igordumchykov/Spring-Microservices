package com.brownfield.pss.book.jms;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brownfield.pss.book.service.BookingServiceImpl;
import com.brownfield.pss.book.util.BookingStatus;

@Component
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Receiver {

    @Autowired
    private BookingServiceImpl bookingService;

    @RabbitListener(queues = "CheckINQ")
    public void processMessage(long bookingID) {
        log.debug("Booking id received: {}", bookingID);
        bookingService.updateStatus(BookingStatus.CHECKED_IN, bookingID);
    }

}