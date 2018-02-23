package com.jdum.booking.checkin.util;

import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.common.dto.CheckInRecordDTO;

/**
 * @author idumchykov
 * @since 2/22/18
 */
public class TestDataCreator {

    public static String FIRST_NAME = "TestName";
    public static String LAST_NAME = "TestSurname";
    public static String BUS_NUMBER = "BH100";
    public static String TRIP_DATE = "22-Jan-16";
    public static Long BOOK_ID = 1L;
    public static Long CHECK_IN_ID = 1L;

    public static CheckInRecord constructCheckinRecord() {
        return new CheckInRecord()
                .setBookingId(BOOK_ID)
                .setBusNumber(BUS_NUMBER)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setTripDate(TRIP_DATE);
    }

    public static CheckInRecordDTO constructCheckinRecordDTO() {
        return new CheckInRecordDTO()
                .setBookingId(BOOK_ID)
                .setBusNumber(BUS_NUMBER)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setTripDate(TRIP_DATE);
    }
}
