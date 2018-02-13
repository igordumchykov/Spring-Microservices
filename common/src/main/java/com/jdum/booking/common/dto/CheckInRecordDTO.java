package com.jdum.booking.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CheckInRecordDTO extends BaseDTO {

    private String lastName;
    private String firstName;
    private String seatNumber;
    private Date checkInTime;
    private String busNumber;
    private String tripDate;
    private Long bookingId;

    public CheckInRecordDTO(String firstName, String lastName, String seatNumber, Date checkInTime, String busNumber,
                            String tripDate, long bookingId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.seatNumber = seatNumber;
        this.checkInTime = checkInTime;
        this.busNumber = busNumber;
        this.tripDate = tripDate;
        this.bookingId = bookingId;
    }
}
