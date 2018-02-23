package com.jdum.booking.prices.service;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.prices.mapper.PriceMapper;
import com.jdum.booking.prices.model.Price;
import com.jdum.booking.prices.repository.PricesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.prices.util.TestDataCreator.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PricesServiceTest {

    @InjectMocks
    private PricesServiceImpl pricesService;

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PriceMapper priceMapper;

    @Test
    public void shouldReturnPrice() throws Exception {

        Price price = constructPrice();
        PriceDTO priceDTO = constructPriceDTO();

        when(pricesRepository.getPriceByBusNumberAndTripDate(BUS_NUMBER, TRIP_DATE)).thenReturn(price);
        when(priceMapper.map(price, PriceDTO.class)).thenReturn(priceDTO);

        PriceDTO actualPrice = pricesService.getPrice(BUS_NUMBER, TRIP_DATE);

        assertEquals(priceDTO, actualPrice);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFound() throws Exception {

        when(pricesRepository.getPriceByBusNumberAndTripDate(BUS_NUMBER, TRIP_DATE)).thenReturn(null);

        pricesService.getPrice(BUS_NUMBER, TRIP_DATE);
    }
}