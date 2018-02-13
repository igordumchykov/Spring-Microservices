package com.jdum.booking.checkin.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CheckInRecord extends BaseEntity {

    private String lastName;
    private String firstName;
    private String seatNumber;
    private Date checkInTime;
    private String busNumber;
    private String tripDate;
    private long bookingId;

}
