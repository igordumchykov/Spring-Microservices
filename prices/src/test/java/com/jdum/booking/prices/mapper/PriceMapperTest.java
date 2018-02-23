package com.jdum.booking.prices.mapper;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.prices.model.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.prices.util.TestDataCreator.constructPrice;
import static com.jdum.booking.prices.util.TestDataCreator.constructPriceDTO;
import static org.junit.Assert.assertEquals;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PriceMapperTest {

    @InjectMocks
    private PriceMapper mapper;

    @Test
    public void shouldMapBetweenDomainAndDTO() throws Exception {

        Price domain = constructPrice();
        PriceDTO dto = constructPriceDTO();

        PriceDTO actualDTO = mapper.map(domain, PriceDTO.class);
        Price actualDomain = mapper.map(dto, Price.class);

        assertEquals(domain, actualDomain);
        assertEquals(dto, actualDTO);
    }
}