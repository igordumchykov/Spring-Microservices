package com.jdum.booking.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Data
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
}
