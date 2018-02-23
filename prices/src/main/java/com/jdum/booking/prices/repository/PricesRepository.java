package com.jdum.booking.prices.repository;

import com.jdum.booking.prices.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {

    Price getPriceByBusNumberAndTripDate(String busNumber, String tripDate);
}
