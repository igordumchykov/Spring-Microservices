package com.jdum.booking.search.service;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.search.model.Inventory;
import com.jdum.booking.search.model.Trip;
import com.jdum.booking.search.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<TripDTO> search(SearchQuery query) {
        log.debug("Search for: {}", query);
        return ofNullable(priceRepository.findByOriginAndDestinationAndTripDate(query.getOrigin(), query.getDestination(), query.getTripDate()))
                .map(trips -> trips.stream()
                        .filter(trip -> trip.getInventory().getCount() >= 0)
                        .map(trip -> mapperFacade.map(trip, TripDTO.class))
                        .collect(toList()))
                .orElseGet(ArrayList::new);
    }

    @Override
    public void updateInventory(String busNumber, String tripDate, int inventory) {
        log.debug("Updating inventory for trip: {}, inventory: {} ", busNumber, inventory);
        Trip trip = priceRepository.findByBusNumberAndTripDate(busNumber, tripDate);
        Inventory inv = trip.getInventory();
        inv.setCount(inventory);
        priceRepository.save(trip);
    }
}
