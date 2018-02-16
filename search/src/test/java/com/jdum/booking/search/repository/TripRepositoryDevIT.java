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

    private static String ORIGIN = "SEA";
    private static String DESTINATION = "SFO";
    private static String TRIP_DATE = "22-JAN-16";
    private static String BUS_NUMBER = "BF100";

    @Autowired
    private TripRepository tripRepository;

    @Before
    public void setUp() throws Exception {
        tripRepository.save(newArrayList(
                new Trip("BF100", "SEA", "SFO", "22-JAN-16",
                        new Price("100", "USD"), new Inventory(100))));
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