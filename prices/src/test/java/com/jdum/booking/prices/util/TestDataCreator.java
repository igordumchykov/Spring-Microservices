package com.jdum.booking.prices.util;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.prices.model.Price;

/**
 * @author idumchykov
 * @since 2/22/18
 */
public class TestDataCreator {

    public static String BUS_NUMBER = "BH100";
    public static String PRICE_AMOUNT = "100";
    public static String TRIP_DATE = "22-Jan-16";

    public static PriceDTO constructPriceDTO() {
        return PriceDTO.builder()
                .tripDate(TRIP_DATE)
                .busNumber(BUS_NUMBER)
                .priceAmount(PRICE_AMOUNT)
                .build();
    }

    public static Price constructPrice() {
        return new Price()
                .setBusNumber(BUS_NUMBER)
                .setPriceAmount(PRICE_AMOUNT)
                .setTripDate(TRIP_DATE);
    }
}
