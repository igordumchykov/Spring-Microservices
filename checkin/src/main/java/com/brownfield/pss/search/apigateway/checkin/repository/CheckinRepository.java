package com.brownfield.pss.search.apigateway.checkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.search.apigateway.checkin.model.CheckInRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<CheckInRecord, Long> {

}
