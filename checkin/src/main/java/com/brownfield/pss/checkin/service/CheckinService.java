package com.brownfield.pss.checkin.service;

import com.brownfield.pss.checkin.model.CheckInRecord;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface CheckinService {

    long checkIn(CheckInRecord checkIn);

    CheckInRecord getCheckInRecord(long id);
}
