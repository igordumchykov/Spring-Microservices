package com.brownfield.pss.search.apigateway.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.search.apigateway.book.model.BookingRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingRecord, Long> {
	
}
