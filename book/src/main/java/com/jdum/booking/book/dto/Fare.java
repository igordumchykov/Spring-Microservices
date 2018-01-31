package com.jdum.booking.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Fare {

    private String flightNumber;
    private String flightDate;
    private String fare;

    @Override
    public String toString() {
        return "Fare [flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", fare=" + fare + "]";
    }

}