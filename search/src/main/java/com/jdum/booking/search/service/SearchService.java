package com.jdum.booking.search.service;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.common.exceptions.NotFoundException;

import java.util.List;

/**
 * @author idumchykov
 * @since 10/4/17
 */
public interface SearchService {
    List<TripDTO> search(SearchQuery query) throws NotFoundException;

    void updateInventory(String flightNumber, String flightDate, int inventory);
}
