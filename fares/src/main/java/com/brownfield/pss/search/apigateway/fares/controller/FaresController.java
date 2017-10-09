package com.brownfield.pss.search.apigateway.fares.controller;

import com.brownfield.pss.search.apigateway.fares.service.FareServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.search.apigateway.fares.model.Fare;

@RestController
@CrossOrigin
@RequestMapping("/fares")
@NoArgsConstructor
@AllArgsConstructor
public class FaresController {

    @Autowired
    private FareServiceImpl faresComponent;

    @RequestMapping("/get")
    public Fare getFare(@RequestParam(value = "flightNumber") String flightNumber,
                        @RequestParam(value = "flightDate") String flightDate) {
        return faresComponent.getFare(flightNumber, flightDate);
    }
}
