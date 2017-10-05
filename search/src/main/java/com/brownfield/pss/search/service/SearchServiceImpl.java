package com.brownfield.pss.search.service;

import com.brownfield.pss.search.dto.SearchQuery;
import com.brownfield.pss.search.model.Flight;
import com.brownfield.pss.search.model.Inventory;
import com.brownfield.pss.search.repository.FlightRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private FlightRepository flightRepository;

    @Override
    public List<Flight> search(SearchQuery query) {
        log.debug("Search for: {}", query);
        return ofNullable(flightRepository.findByOriginAndDestinationAndFlightDate(query.getOrigin(), query.getDestination(), query.getFlightDate()))
                .map(f -> f.stream().filter(flight -> flight.getInventory().getCount() >= 0).collect(toList()))
                .orElseGet(ArrayList::new);
    }

    @Override
    public void updateInventory(String flightNumber, String flightDate, int inventory) {
        log.debug("Updating inventory for flight: {}, inventory: {} ", flightNumber, inventory);
        Flight flight = flightRepository.findByFlightNumberAndFlightDate(flightNumber, flightDate);
        Inventory inv = flight.getInventory();
        inv.setCount(inventory);
        flightRepository.save(flight);
    }
}
