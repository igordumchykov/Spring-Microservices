package com.jdum.booking.prices.service;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.common.exceptions.NotFoundException;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface PricesService {

    PriceDTO getPrice(String busNumber, String tripDate) throws NotFoundException;
}
