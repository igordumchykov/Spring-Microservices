package com.jdum.booking.prices.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Price extends BaseEntity {
    private String busNumber;
    private String tripDate;
    private String priceAmount;
}
