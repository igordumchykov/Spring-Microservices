package com.jdum.booking.fares.controller;

import com.jdum.booking.fares.model.Fare;
import com.jdum.booking.fares.service.FaresService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@NoArgsConstructor
@AllArgsConstructor
public class FaresController {

    @Autowired
    private FaresService fareService;

    @RequestMapping("/get")
    public Fare getFare(@RequestParam(value = "flightNumber") String flightNumber,
                        @RequestParam(value = "flightDate") String flightDate) {
        return fareService.getFare(flightNumber, flightDate);
    }
}
