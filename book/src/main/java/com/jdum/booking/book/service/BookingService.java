package com.jdum.booking.book.service;

import com.jdum.booking.book.model.BookingStatus;
import com.jdum.booking.common.dto.BookingRecordDTO;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface BookingService {

    long book(BookingRecordDTO bookingRecord);

    BookingRecordDTO getBooking(long id);

    void updateStatus(BookingStatus status, long bookingId);
}
