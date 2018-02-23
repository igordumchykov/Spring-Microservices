package com.jdum.booking.search.util;

import com.google.common.collect.ImmutableMap;
import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.model.Price;
import com.jdum.booking.search.model.Trip;

import java.util.Map;

import static com.jdum.booking.search.constants.Constants.*;

/**
 * @author idumchykov
 * @since 2/22/18
 */
public class TestDataCreator {

    public static String ORIGIN = "SEA";
    public static String DESTINATION = "SFO";
    public static String TRIP_DATE = "22-JAN-16";
    public static String BUS_NUMBER = "BF100";
    public static String PRICE_AMOUNT = "100";
    public static String PRICE_CURRENCY = "USD";
    public static int INVENTORY_COUNT = 100;

    public static Trip constructTrip() {
        return new Trip()
                .setBusNumber(BUS_NUMBER)
                .setDestination(DESTINATION)
                .setOrigin(ORIGIN)
                .setTripDate(TRIP_DATE)
                .setInventory(new Inventory(INVENTORY_COUNT))
                .setPrice(new Price(PRICE_AMOUNT, PRICE_CURRENCY));
    }

    public static TripDTO constructTripDTO() {
        return TripDTO.builder()
                .busNumber(BUS_NUMBER)
                .destination(DESTINATION)
                .tripDate(TRIP_DATE)
                .origin(ORIGIN)
                .price(PriceDTO.builder()
                        .priceAmount(PRICE_AMOUNT)
                        .busNumber(BUS_NUMBER)
                        .tripDate(TRIP_DATE)
                        .currency(PRICE_CURRENCY)
                        .build())
                .build();
    }

    public static SearchQuery constructSearchQuery() {
        return new SearchQuery()
                .setDestination(DESTINATION)
                .setOrigin(ORIGIN)
                .setTripDate(TRIP_DATE);
    }

    public static Map<String, Object> constructMessage() {
        return ImmutableMap.<String, Object>builder()
                .put(BUS_NUMBER_MSG, BUS_NUMBER)
                .put(TRIP_DATE_MSG, TRIP_DATE)
                .put(NEW_INVENTORY_MSG, INVENTORY_COUNT)
                .build();
    }
}
