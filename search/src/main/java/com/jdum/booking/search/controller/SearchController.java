package com.jdum.booking.search.controller;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.search.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<TripDTO> search(@RequestBody SearchQuery query) {
        log.debug("Input: {}", query);
        return searchService.search(query);
    }

    @RequestMapping("/test")
    public String getHub() {
        log.debug("Searching for Hub, received from search-apigateway ");
        return "Response from search service";
    }

}
