package com.jdum.booking.checkin.service;

import com.jdum.booking.checkin.jms.Sender;
import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.checkin.repository.CheckinRepository;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private Sender sender;

    @Override
    public Long checkIn(CheckInRecordDTO checkInRecordDTO) {

        CheckInRecord checkInRecord = mapperFacade.map(checkInRecordDTO, CheckInRecord.class);

        log.debug("Saving checkin: {}", checkInRecord);

        checkInRecord.setCheckInTime(new Date());
        Long id = checkinRepository.save(checkInRecord).getId();

        log.debug("Successfully saved checkin with id: {}", id);

        sender.send(checkInRecordDTO.getBookingId());

        return id;
    }

    @Override
    public CheckInRecordDTO getCheckInRecord(Long id) {
        CheckInRecord checkInRecord = checkinRepository.findOne(id);
        return mapperFacade.map(checkInRecord, CheckInRecordDTO.class);
    }

}	
