package com.brownfield.pss.search.apigateway.checkin.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.search.apigateway.checkin.service.CheckinServiceImpl;
import com.brownfield.pss.search.apigateway.checkin.model.CheckInRecord;

@RestController
@CrossOrigin
@RequestMapping("/checkin")
@AllArgsConstructor
@NoArgsConstructor
public class CheckInController {

    @Autowired
    private CheckinServiceImpl checkInComponent;

    @RequestMapping("/get/{id}")
    public CheckInRecord getCheckIn(@PathVariable long id) {
        return checkInComponent.getCheckInRecord(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long checkIn(@RequestBody CheckInRecord checkIn) {
        return checkInComponent.checkIn(checkIn);
    }

}
