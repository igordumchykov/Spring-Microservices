package com.jdum.booking.prices.repository;

import com.jdum.booking.prices.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {
    Price getPriceByBusNumberAndTripDate(String flightNumber, String flightDate);
}
