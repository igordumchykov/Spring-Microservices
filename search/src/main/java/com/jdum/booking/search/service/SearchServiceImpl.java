package com.jdum.booking.search.service;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.search.mapper.TripMapper;
import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.model.Trip;
import com.jdum.booking.search.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<TripDTO> search(SearchQuery query) throws NotFoundException {

        log.debug("Search for: {}", query);
        List<Trip> foundTrips = tripRepository.findByOriginAndDestinationAndTripDate(
                query.getOrigin(), query.getDestination(), query.getTripDate())
                .stream()
                .filter(trip -> trip.getInventory().getCount() > 0)
                .collect(toList());

        if (CollectionUtils.isEmpty(foundTrips)) {
            throw new NotFoundException(
                    String.format("Flights for origin %s, destination %s, trip date %s not found",
                            query.getOrigin(), query.getDestination(), query.getTripDate()));
        }

        return foundTrips.stream()
                .map(trip -> tripMapper.map(trip, TripDTO.class))
                .collect(toList());
    }

    @Override
    public void updateInventory(String busNumber, String tripDate, int inventory) throws NotFoundException {

        log.debug("Updating inventory for trip: {}, inventory: {} ", busNumber, inventory);
        Trip trip = ofNullable(tripRepository.findByBusNumberAndTripDate(busNumber, tripDate))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Trip for busNumber %s and tripDate %s not found", busNumber, tripDate)));

        Inventory inv = trip.getInventory();
        inv.setCount(inventory);

        tripRepository.save(trip);
    }
}
