package com.jdum.booking.checkin.mapper;

import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.checkin.util.TestDataCreator.constructCheckinRecord;
import static com.jdum.booking.checkin.util.TestDataCreator.constructCheckinRecordDTO;
import static org.junit.Assert.assertEquals;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckInRecordMapperTest {

    @InjectMocks
    private CheckInRecordMapper mapper;

    @Test
    public void shouldMapBetweenDomainAndDTO() throws Exception {

        CheckInRecord domain = constructCheckinRecord();
        CheckInRecordDTO dto = constructCheckinRecordDTO();

        CheckInRecordDTO actualDTO = mapper.map(domain, CheckInRecordDTO.class);
        CheckInRecord actualDomain = mapper.map(dto, CheckInRecord.class);

        assertEquals(domain, actualDomain);
        assertEquals(dto, actualDTO);
    }
}