package com.jdum.booking.prices.controller;

import com.jdum.booking.prices.model.Price;
import com.jdum.booking.prices.service.PricesService;
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
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @RequestMapping("/get")
    public Price getPrice(@RequestParam(value = "busNumber") String busNumber,
                          @RequestParam(value = "tripDate") String tripDate) {
        return pricesService.getPrice(busNumber, tripDate);
    }
}
