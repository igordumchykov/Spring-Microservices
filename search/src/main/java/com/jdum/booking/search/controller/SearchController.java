package com.jdum.booking.search.controller;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor
class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/get")
    public List<TripDTO> search(@Valid @RequestBody SearchQuery query) throws NotFoundException {
        log.debug("Input: {}", query);
        return searchService.search(query);
    }

    @RequestMapping("/test")
    public String getHub() {
        log.debug("Searching for Hub, received from search-apigateway ");
        return "Response from search service";
    }

}
