package com.jdum.booking.prices.service;

import com.jdum.booking.prices.model.Price;
import com.jdum.booking.prices.repository.PricesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesRepository pricesRepository;

    public Price getPrice(String busNumber, String tripDate) {
        log.debug("Looking for prices busNumber: {}, tripDate: {}", busNumber, tripDate);
        return pricesRepository.getPriceByBusNumberAndTripDate(busNumber, tripDate);
    }
}
