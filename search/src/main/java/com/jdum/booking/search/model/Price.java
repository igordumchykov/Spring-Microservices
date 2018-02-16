package com.jdum.booking.search.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SEARCH_PRICE")
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICE_ID")
    private Long id;

    @Column(name = "PRICE_AMOUNT")
    private String priceAmount;

    @Column(name = "CURRENCY")
    private String currency;

    public Price(String priceAmount, String currency) {
        this.priceAmount = priceAmount;
        this.currency = currency;
    }
}
