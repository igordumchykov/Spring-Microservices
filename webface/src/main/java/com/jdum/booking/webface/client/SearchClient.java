package com.jdum.booking.webface.client;

import com.jdum.booking.webface.dto.Flight;
import com.jdum.booking.webface.dto.SearchQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author idumchykov
 * @since 1/24/18
 */
@FeignClient(name = "apigateway")
public interface SearchClient {

    @RequestMapping(value = "/search/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Flight> getFlights(SearchQuery searchQuery);
}
