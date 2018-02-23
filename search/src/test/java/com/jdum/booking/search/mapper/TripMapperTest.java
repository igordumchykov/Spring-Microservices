package com.jdum.booking.search.mapper;

import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.search.model.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.search.util.TestDataCreator.constructTrip;
import static com.jdum.booking.search.util.TestDataCreator.constructTripDTO;
import static org.junit.Assert.assertEquals;

/**
 * @author idumchykov
 * @since 2/23/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TripMapperTest {

    @InjectMocks
    private TripMapper mapper;

    @Test
    public void shouldMapBetweenDomainAndDTO() throws Exception {

        Trip domain = constructTrip();
        TripDTO dto = constructTripDTO();

        TripDTO actualDTO = mapper.map(domain, TripDTO.class);
        Trip actualDomain = mapper.map(dto, Trip.class);

        assertEquals(domain.getOrigin(), actualDomain.getOrigin());
        assertEquals(domain.getBusNumber(), actualDomain.getBusNumber());
        assertEquals(domain.getDestination(), actualDomain.getDestination());
        assertEquals(domain.getTripDate(), actualDomain.getTripDate());
        assertEquals(domain.getPrice().getPriceAmount(), actualDomain.getPrice().getPriceAmount());

        assertEquals(dto.getOrigin(), actualDTO.getOrigin());
        assertEquals(dto.getBusNumber(), actualDTO.getBusNumber());
        assertEquals(dto.getDestination(), actualDTO.getDestination());
        assertEquals(dto.getTripDate(), actualDTO.getTripDate());
        assertEquals(dto.getPrice().getPriceAmount(), actualDTO.getPrice().getPriceAmount());
        assertEquals(dto.getPrice().getCurrency(), actualDTO.getPrice().getCurrency());
    }
}