package com.jdum.booking.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdum.booking.checkin.model.CheckInRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<CheckInRecord, Long> {

}
