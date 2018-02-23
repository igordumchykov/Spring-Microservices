package com.jdum.booking.search.service;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.search.mapper.TripMapper;
import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.model.Trip;
import com.jdum.booking.search.repository.TripRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.jdum.booking.search.util.TestDataCreator.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author idumchykov
 * @since 2/14/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchServiceTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private TripMapper tripMapper;

    @Captor
    private ArgumentCaptor<Trip> tripCaptor;

    @Test
    public void shouldUpdateInventory() throws Exception {

        Trip trip = constructTrip();

        when(tripRepository.findByBusNumberAndTripDate(BUS_NUMBER, TRIP_DATE)).thenReturn(trip);
        when(tripRepository.save(tripCaptor.capture())).then(answer -> null);

        searchService.updateInventory(BUS_NUMBER, TRIP_DATE, INVENTORY_COUNT);

        assertEquals(INVENTORY_COUNT, tripCaptor.getValue().getInventory().getCount());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenUpdateInventory() throws Exception {

        when(tripRepository.findByBusNumberAndTripDate(BUS_NUMBER, TRIP_DATE)).thenReturn(null);

        searchService.updateInventory(BUS_NUMBER, TRIP_DATE, INVENTORY_COUNT);
    }

    @Test
    public void shouldReturnTripWhenSearch() throws Exception {

        Trip trip = constructTrip();
        TripDTO tripDTO = constructTripDTO();
        SearchQuery searchQuery = constructSearchQuery();

        when(tripRepository.findByOriginAndDestinationAndTripDate(ORIGIN, DESTINATION, TRIP_DATE))
                .thenReturn(newArrayList(trip));

        when(tripMapper.map(trip, TripDTO.class)).thenReturn(tripDTO);

        List<TripDTO> actualTripDTOs = searchService.search(searchQuery);

        assertEquals(1, actualTripDTOs.size());
        assertEquals(tripDTO, actualTripDTOs.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenRepositoryReturnsEmptyList() throws Exception {

        SearchQuery searchQuery = constructSearchQuery();

        when(tripRepository.findByOriginAndDestinationAndTripDate(ORIGIN, DESTINATION, TRIP_DATE))
                .thenReturn(Collections.emptyList());

        searchService.search(searchQuery);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenInventoryCountIsZero() throws Exception {

        Trip trip = constructTrip()
                .setInventory(new Inventory(0));
        SearchQuery searchQuery = constructSearchQuery();

        when(tripRepository.findByOriginAndDestinationAndTripDate(ORIGIN, DESTINATION, TRIP_DATE))
                .thenReturn(newArrayList(trip));

        searchService.search(searchQuery);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenInventoryCountLessZero() throws Exception {

        Trip trip = constructTrip()
                .setInventory(new Inventory(-1));
        SearchQuery searchQuery = constructSearchQuery();

        when(tripRepository.findByOriginAndDestinationAndTripDate(ORIGIN, DESTINATION, TRIP_DATE))
                .thenReturn(newArrayList(trip));

        searchService.search(searchQuery);
    }
}