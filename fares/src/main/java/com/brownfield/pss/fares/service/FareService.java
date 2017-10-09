package com.brownfield.pss.fares.service;

import com.brownfield.pss.fares.model.Fare;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface FareService {
    Fare getFare(String flightNumber, String flightDate);
}
