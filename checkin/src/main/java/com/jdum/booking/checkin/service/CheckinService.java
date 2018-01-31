package com.jdum.booking.checkin.service;

import com.jdum.booking.checkin.model.CheckInRecord;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface CheckinService {

    long checkIn(CheckInRecord checkIn);

    CheckInRecord getCheckInRecord(long id);
}
