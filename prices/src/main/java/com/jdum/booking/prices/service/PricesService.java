package com.jdum.booking.prices.service;

import com.jdum.booking.prices.model.Price;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface PricesService {
    Price getPrice(String flightNumber, String flightDate);
}
