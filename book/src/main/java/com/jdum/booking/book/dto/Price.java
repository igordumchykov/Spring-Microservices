package com.jdum.booking.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Price {

    private String busNumber;
    private String tripDate;
    private String priceAmount;

    @Override
    public String toString() {
        return "PriceDTO [busNumber=" + busNumber + ", tripDate=" + tripDate + ", priceAmount=" + priceAmount + "]";
    }

}