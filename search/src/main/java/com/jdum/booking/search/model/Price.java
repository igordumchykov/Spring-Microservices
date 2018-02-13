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
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_id")
    private long id;

    private String priceAmount;
    private String currency;

    public Price(String priceAmount, String currency) {
        this.priceAmount = priceAmount;
        this.currency = currency;
    }
}
