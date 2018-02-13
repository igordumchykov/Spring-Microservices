package com.jdum.booking.search.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends BaseEntity {

    private String busNumber;
    private String origin;
    private String destination;
    private String tripDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inv_id")
    private Inventory inventory;

}
