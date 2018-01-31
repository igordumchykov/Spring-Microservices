package com.jdum.booking.book.jms;

import com.jdum.booking.book.util.BookingStatus;
import com.jdum.booking.book.service.BookingServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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