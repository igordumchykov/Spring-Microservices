package com.brownfield.pss.search.controller;

import com.brownfield.pss.search.dto.SearchQuery;
import com.brownfield.pss.search.model.Flight;
import com.brownfield.pss.search.service.SearchServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class SearchRestController {

    @Autowired
    private SearchServiceImpl searchComponent;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<Flight> search(@RequestBody SearchQuery query) {
        log.debug("Input: {}", query);
        return searchComponent.search(query);
    }

    @RequestMapping("/hub")
    public String getHub() {
        log.info("Searching for Hub, received from search-apigateway ");
        return "SFO";
    }

}
