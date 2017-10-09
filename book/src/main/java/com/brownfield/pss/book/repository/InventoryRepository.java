package com.brownfield.pss.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.book.model.Inventory;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
	
}
