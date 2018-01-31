package com.jdum.booking.fares.service;

import com.jdum.booking.fares.model.Fare;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface FaresService {
    Fare getFare(String flightNumber, String flightDate);
}
