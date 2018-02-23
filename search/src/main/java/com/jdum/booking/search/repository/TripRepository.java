package com.jdum.booking.search.repository;


import com.jdum.booking.search.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByOriginAndDestinationAndTripDate(String origin, String destination, String tripDate);

    Trip findByBusNumberAndTripDate(String busNumber, String tripDate);
} 