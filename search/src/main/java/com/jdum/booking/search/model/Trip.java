package com.jdum.booking.search.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@Table(name = "SEARCH_TRIP")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends BaseEntity {

    @Column(name = "BUS_NUMBER")
    private String busNumber;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "TRIP_DATE")
    private String tripDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRICE_ID")
    private Price price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INV_ID")
    private Inventory inventory;

}
