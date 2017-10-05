package com.brownfield.pss.fares.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.fares.model.Fare;
import org.springframework.stereotype.Repository;

@Repository
public interface FaresRepository extends JpaRepository<Fare, Long> {
    Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
