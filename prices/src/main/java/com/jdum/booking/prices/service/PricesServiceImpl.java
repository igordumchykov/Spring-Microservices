package com.jdum.booking.prices.service;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.prices.mapper.PriceMapper;
import com.jdum.booking.prices.repository.PricesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private PriceMapper priceMapper;

    public PriceDTO getPrice(String busNumber, String tripDate) throws NotFoundException {

        log.debug("Get price for  busNumber: {}, tripDate: {}", busNumber, tripDate);

        return ofNullable(pricesRepository.getPriceByBusNumberAndTripDate(busNumber, tripDate))
                .map(price -> priceMapper.map(price, PriceDTO.class))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Price for busNumber %s and tripDate %s not found", busNumber, tripDate)));
    }
}
