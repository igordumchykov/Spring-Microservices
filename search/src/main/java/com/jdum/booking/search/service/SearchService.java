package com.jdum.booking.search.service;

import com.jdum.booking.search.model.Flight;
import com.jdum.booking.search.dto.SearchQuery;

import java.util.List;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface SearchService {
    List<Flight> search(SearchQuery query);

    void updateInventory(String flightNumber, String flightDate, int inventory);
}
