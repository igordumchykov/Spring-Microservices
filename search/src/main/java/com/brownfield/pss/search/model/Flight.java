package com.brownfield.pss.search.model;

import com.brownfield.pss.common.model.BaseEntity;
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
public class Flight extends BaseEntity {

    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fare_Id")
    private Fares fares;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inv_Id")
    private Inventory inventory;

}
