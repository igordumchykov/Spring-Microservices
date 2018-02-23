package com.jdum.booking.checkin.service;

import com.jdum.booking.checkin.jms.Sender;
import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.checkin.repository.CheckinRepository;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private MapperFacade checkInMapper;

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private Sender sender;

    @Override
    public Long checkIn(CheckInRecordDTO checkInRecordDTO) {

        CheckInRecord checkInRecord = checkInMapper.map(checkInRecordDTO, CheckInRecord.class);

        log.debug("Saving checkIn: {}", checkInRecord);

        checkInRecord.setCheckInTime(new Date());
        Long id = checkinRepository.save(checkInRecord).getId();

        log.debug("Successfully saved checkIn with id: {}", id);

        sender.send(checkInRecordDTO.getBookingId());

        return id;
    }

    @Override
    public CheckInRecordDTO getCheckInRecord(Long id) throws NotFoundException {

        CheckInRecord checkInRecord = ofNullable(checkinRepository.findOne(id))
                .orElseThrow(() -> new NotFoundException(String.format("CheckInRecord for id %d not found", id)));

        return checkInMapper.map(checkInRecord, CheckInRecordDTO.class);
    }

}	
