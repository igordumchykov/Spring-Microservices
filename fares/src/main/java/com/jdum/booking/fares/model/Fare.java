package com.jdum.booking.fares.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Fare  extends BaseEntity {
    private String flightNumber;
    private String flightDate;
    private String fare;
}
