package com.jdum.booking.checkin.controller;

import com.jdum.booking.checkin.service.CheckinService;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.jdum.booking.checkin.constants.REST.CHECK_IN_CREATE_PATH;
import static com.jdum.booking.checkin.constants.REST.CHECK_IN_GET_PATH;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CheckInController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping(CHECK_IN_GET_PATH)
    public CheckInRecordDTO getCheckIn(@PathVariable Long id) {
        return checkinService.getCheckInRecord(id);
    }

    @PostMapping(CHECK_IN_CREATE_PATH)
    public Long checkIn(@RequestBody CheckInRecordDTO checkIn) {
        return checkinService.checkIn(checkIn);
    }

}
