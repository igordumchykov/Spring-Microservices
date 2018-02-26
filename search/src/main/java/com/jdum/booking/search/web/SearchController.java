package com.jdum.booking.search.web;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.jdum.booking.search.constants.REST.SEARCH_GET_PATH;
import static com.jdum.booking.search.constants.REST.SEARCH_TEST_PATH;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor
class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(SEARCH_GET_PATH)
    public List<TripDTO> search(@Valid @RequestBody SearchQuery query) {

        log.debug("Input: {}", query);

        return searchService.search(query);
    }

    //is used only for testing with api gateway, this service and hystrix circuit breaker integration
    @RequestMapping(SEARCH_TEST_PATH)
    public String getHub() {
        log.debug("Searching for Hub, received from search-apigateway");
        return "Response from search service";
    }

}
