package com.jdum.booking.common.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TripDTO extends BaseDTO {

    private String busNumber;
    private String origin;
    private String destination;
    private String tripDate;
    private PriceDTO price;

    public TripDTO(BookingRecordDTO booking) {
        busNumber = booking.getBusNumber();
        origin = booking.getOrigin();
        destination = booking.getDestination();
        tripDate = booking.getTripDate();
        price = new PriceDTO(booking.getPrice());
    }

    public TripDTO(String busNumber, String origin, String destination, String tripDate, String price) {
        this.busNumber = busNumber;
        this.origin = origin;
        this.destination = destination;
        this.tripDate = tripDate;
        this.price = new PriceDTO(price);
    }
}
