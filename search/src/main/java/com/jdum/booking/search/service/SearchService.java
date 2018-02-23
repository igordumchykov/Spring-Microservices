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

    /**
     * call repository and update the priceAmount for the given trip
     */
    void updateInventory(String busNumber, String tripDate, int inventory) throws NotFoundException;
}
