package com.brownfield.pss.search.apigateway.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Fares {

    private long id;

    private String fare;
    private String currency;

    public Fares(String fare, String currency) {
        this.fare = fare;
        this.currency = currency;
    }

}
