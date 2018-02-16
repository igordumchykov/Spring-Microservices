package com.jdum.booking.common.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookingRecordDTO extends BaseDTO {

    private String busNumber;
    private String origin;
    private String destination;
    private String tripDate;
    private Date bookingDate;
    private String price;
    private String status;
    private Set<PassengerDTO> passengers;

    public BookingRecordDTO(TripDTO trip) {
        this.busNumber = trip.getBusNumber();
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.tripDate = trip.getTripDate();
        this.price = trip.getPrice().getPriceAmount();
    }
}
