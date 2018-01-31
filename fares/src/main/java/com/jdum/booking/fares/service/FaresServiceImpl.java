package com.jdum.booking.fares.service;

import com.jdum.booking.fares.model.Fare;
import com.jdum.booking.fares.repository.FaresRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class FaresServiceImpl implements FaresService {

    @Autowired
    private FaresRepository faresRepository;

    public Fare getFare(String flightNumber, String flightDate) {
        log.debug("Looking for fares flightNumber: {}, flightDate: {}", flightNumber, flightDate);
        return faresRepository.getFareByFlightNumberAndFlightDate(flightNumber, flightDate);
    }
}
