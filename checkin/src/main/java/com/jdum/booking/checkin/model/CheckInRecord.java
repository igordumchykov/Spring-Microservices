package com.jdum.booking.checkin.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CHECK_IN_RECORD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CheckInRecord extends BaseEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    @Column(name = "CHECK_IN_TIME")
    private Date checkInTime;

    @Column(name = "BUS_NUMBER")
    private String busNumber;

    @Column(name = "TRIP_DATE")
    private String tripDate;

    @Column(name = "BOOKING_ID")
    private Long bookingId;

}
