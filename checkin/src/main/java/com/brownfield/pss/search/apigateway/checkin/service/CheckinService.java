package com.brownfield.pss.search.apigateway.checkin.service;

import com.brownfield.pss.search.apigateway.checkin.model.CheckInRecord;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface CheckinService {

    long checkIn(CheckInRecord checkIn);

    CheckInRecord getCheckInRecord(long id);
}
