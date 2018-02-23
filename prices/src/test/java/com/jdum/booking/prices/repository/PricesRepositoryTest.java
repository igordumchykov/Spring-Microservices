package com.jdum.booking.prices.repository;

import com.jdum.booking.prices.model.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jdum.booking.prices.util.TestDataCreator.*;
import static org.junit.Assert.assertEquals;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles({"dev"})
public class PricesRepositoryTest {

    @Autowired
    private PricesRepository pricesRepository;

    @Before
    public void setUp() throws Exception {
        pricesRepository.save(new Price(BUS_NUMBER, TRIP_DATE, PRICE_AMOUNT));
    }

    @Test
    public void shouldReturnPrice() throws Exception {

        Price actualPrice = pricesRepository.getPriceByBusNumberAndTripDate(BUS_NUMBER, TRIP_DATE);

        assertEquals(BUS_NUMBER, actualPrice.getBusNumber());
        assertEquals(TRIP_DATE, actualPrice.getTripDate());
        assertEquals(PRICE_AMOUNT, actualPrice.getPriceAmount());
    }
}