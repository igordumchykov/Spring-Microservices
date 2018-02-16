package com.jdum.booking.search.model;

import com.jdum.booking.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "SEARCH_PRICE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "PRICE_ID"))
public class Price extends BaseEntity {

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
