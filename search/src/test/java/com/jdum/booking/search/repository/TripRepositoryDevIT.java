package com.jdum.booking.search.repository;

import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.model.Price;
import com.jdum.booking.search.model.Trip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.jdum.booking.search.util.TestDataCreator.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author idumchykov
 * @since 2/14/18
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles({"dev"})
public class TripRepositoryDevIT {

    @Autowired
    private TripRepository tripRepository;

    @Before
    public void setUp() throws Exception {
        tripRepository.save(newArrayList(
                new Trip(BUS_NUMBER, ORIGIN, DESTINATION, TRIP_DATE,
                        new Price(PRICE_AMOUNT, PRICE_CURRENCY), new Inventory(INVENTORY_COUNT))));
    }

    @Test
    public void findByOriginAndDestinationAndTripDate() throws Exception {
        List<Trip> foundTrip = tripRepository.findByOriginAndDestinationAndTripDate(ORIGIN, DESTINATION, TRIP_DATE);

        assertNotNull(foundTrip);
        assertEquals(1, foundTrip.size());
    }

    @Test
    public void findByBusNumberAndTripDate() throws Exception {
        Trip foundTrip = tripRepository.findByBusNumberAndTripDate(BUS_NUMBER, TRIP_DATE);

        assertNotNull(foundTrip);
        assertEquals(ORIGIN, foundTrip.getOrigin());
    }

}