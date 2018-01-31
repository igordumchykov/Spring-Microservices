package com.jdum.booking.search.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Fares implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fare_id")
    private long id;

    private String fare;
    private String currency;

    public Fares(String fare, String currency) {
        this.fare = fare;
        this.currency = currency;
    }
}
