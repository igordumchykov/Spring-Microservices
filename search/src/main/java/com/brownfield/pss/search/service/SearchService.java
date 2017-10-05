package com.brownfield.pss.search.service;

import com.brownfield.pss.search.dto.SearchQuery;
import com.brownfield.pss.search.model.Flight;

import java.util.List;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface SearchService {
    List<Flight> search(SearchQuery query);

    void updateInventory(String flightNumber, String flightDate, int inventory);
}
