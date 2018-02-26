package com.jdum.booking.prices.web;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.prices.service.PricesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jdum.booking.prices.constants.REST.*;
import static com.jdum.booking.prices.util.TestDataCreator.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PricesController.class)
public class PricesControllerTest {

    @MockBean
    private PricesService pricesService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnPrice() throws Exception {

        PriceDTO priceDTO = constructPriceDTO();
        when(pricesService.getPrice(BUS_NUMBER, TRIP_DATE)).thenReturn(priceDTO);

        mockMvc.perform(get(PRICE_GET_PATH)
                .param(BUS_NUMBER_PARAM, BUS_NUMBER)
                .param(TRIP_DATE_PARAM, TRIP_DATE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.busNumber", is(priceDTO.getBusNumber())))
                .andExpect(jsonPath("$.priceAmount", is(priceDTO.getPriceAmount())))
                .andExpect(jsonPath("$.tripDate", is(priceDTO.getTripDate())));
    }

    @Test
    public void shouldReturn404() throws Exception {

        doThrow(NotFoundException.class).when(pricesService).getPrice(BUS_NUMBER, TRIP_DATE);

        mockMvc.perform(get(PRICE_GET_PATH)
                .param(BUS_NUMBER_PARAM, BUS_NUMBER)
                .param(TRIP_DATE_PARAM, TRIP_DATE))
                .andExpect(status().isNotFound());
    }
}