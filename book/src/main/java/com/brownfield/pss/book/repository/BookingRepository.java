package com.brownfield.pss.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.book.model.BookingRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingRecord, Long> {
	
}
