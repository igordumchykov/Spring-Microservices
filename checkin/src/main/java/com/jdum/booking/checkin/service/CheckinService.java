package com.jdum.booking.checkin.service;

import com.jdum.booking.common.dto.CheckInRecordDTO;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface CheckinService {

    long checkIn(CheckInRecordDTO checkIn);

    CheckInRecordDTO getCheckInRecord(long id);
}
