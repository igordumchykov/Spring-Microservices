package com.jdum.booking.webface.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;


@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BookingRecord {
    private long id;

    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;
    private Set<Passenger> passengers;

    public BookingRecord(String flightNumber, String from, String to,
                         String flightDate, Date bookingDate, String fare) {
        this.flightNumber = flightNumber;
        this.origin = from;
        this.destination = to;
        this.flightDate = flightDate;
        this.bookingDate = bookingDate;
        this.fare = fare;
    }
}
