package com.brownfield.pss.search.apigateway.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CheckInRecord {
    private long id;

    private String lastName;
    private String firstName;
    private String seatNumber;
    private Date checkInTime;
    private String flightNumber;
    private String flightDate;
    private long bookingId;

    public CheckInRecord(String lastName, String firstName, String seatNumber, Date checkInTime, String flightNumber,
                         String flightDate, long bookingId) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.seatNumber = seatNumber;
        this.checkInTime = checkInTime;
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.bookingId = bookingId;
    }

}
