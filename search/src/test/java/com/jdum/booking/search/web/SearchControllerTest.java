package com.jdum.booking.search.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.google.common.collect.Lists.newArrayList;
import static com.jdum.booking.search.constants.REST.SEARCH_GET_PATH;
import static com.jdum.booking.search.util.TestDataCreator.constructSearchQuery;
import static com.jdum.booking.search.util.TestDataCreator.constructTripDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author idumchykov
 * @since 2/23/18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @MockBean
    private SearchService searchService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnTrips() throws Exception {

        SearchQuery searchQuery = constructSearchQuery();
        TripDTO tripDTO = constructTripDTO();

        when(searchService.search(searchQuery)).thenReturn(newArrayList(tripDTO));

        mockMvc.perform(post(SEARCH_GET_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(searchQuery)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].busNumber", is(tripDTO.getBusNumber())))
                .andExpect(jsonPath("$.[0].origin", is(tripDTO.getOrigin())))
                .andExpect(jsonPath("$.[0].destination", is(tripDTO.getDestination())))
                .andExpect(jsonPath("$.[0].tripDate", is(tripDTO.getTripDate())))
                .andExpect(jsonPath("$.[0].price.busNumber", is(tripDTO.getPrice().getBusNumber())))
                .andExpect(jsonPath("$.[0].price.tripDate", is(tripDTO.getPrice().getTripDate())))
                .andExpect(jsonPath("$.[0].price.priceAmount", is(tripDTO.getPrice().getPriceAmount())))
                .andExpect(jsonPath("$.[0].price.currency", is(tripDTO.getPrice().getCurrency())));
    }

    @Test
    public void shouldReturn404() throws Exception {

        SearchQuery searchQuery = constructSearchQuery();

        doThrow(NotFoundException.class).when(searchService).search(searchQuery);

        mockMvc.perform(post(SEARCH_GET_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(searchQuery)))
                .andExpect(status().isNotFound());
    }
}