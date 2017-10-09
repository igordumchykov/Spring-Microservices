package com.brownfield.pss.search.apigateway.checkin.model;

import com.brownfield.pss.search.apigateway.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
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
    private String flightNumber;
    private String flightDate;
    private long bookingId;

}
