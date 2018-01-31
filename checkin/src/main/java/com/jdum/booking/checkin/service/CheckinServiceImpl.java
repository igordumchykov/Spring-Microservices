package com.jdum.booking.checkin.service;

import com.jdum.booking.checkin.jms.Sender;
import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.checkin.repository.CheckinRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private Sender sender;

    @Override
    public long checkIn(CheckInRecord checkIn) {
        log.debug("Saving checkin: {}", checkIn);
        checkIn.setCheckInTime(new Date());
        long id = checkinRepository.save(checkIn).getId();
        log.debug("Successfully saved checkin with id: {}", id);
        sender.send(id);
        return id;
    }

    @Override
    public CheckInRecord getCheckInRecord(long id) {
        return checkinRepository.findOne(id);
    }

}	
