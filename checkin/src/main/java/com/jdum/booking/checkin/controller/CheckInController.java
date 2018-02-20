package com.jdum.booking.checkin.controller;

import com.jdum.booking.checkin.service.CheckinService;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@NoArgsConstructor
public class CheckInController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping("/get/{id}")
    public CheckInRecordDTO getCheckIn(@PathVariable Long id) {
        return checkinService.getCheckInRecord(id);
    }

    @PostMapping("/create")
    public Long checkIn(@RequestBody CheckInRecordDTO checkIn) {
        return checkinService.checkIn(checkIn);
    }

}
