package com.jdum.booking.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PriceDTO extends BaseDTO {

    private String busNumber;
    private String tripDate;
    private String priceAmount;
    private String currency;

    public PriceDTO(String priceAmount, String currency) {
        this.priceAmount = priceAmount;
        this.currency = currency;
    }

    public PriceDTO(String priceAmount) {
        this.priceAmount = priceAmount;
    }
}