package com.jdum.booking.prices.web;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.prices.service.PricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.jdum.booking.prices.constants.REST.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping(PRICE_GET_PATH)
    public PriceDTO getPrice(@RequestParam(value = BUS_NUMBER_PARAM) String busNumber,
                             @RequestParam(value = TRIP_DATE_PARAM) String tripDate) {

        return pricesService.getPrice(busNumber, tripDate);
    }
}
