package com.jdum.booking.checkin.service;

import com.jdum.booking.checkin.jms.Sender;
import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.checkin.repository.CheckinRepository;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import com.jdum.booking.common.exceptions.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.checkin.util.TestDataCreator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckinServiceTest {

    @InjectMocks
    private CheckinServiceImpl checkinService;

    @Mock
    private MapperFacade checkInMapper;

    @Mock
    private CheckinRepository checkinRepository;

    @Mock
    private Sender sender;

    @Captor
    private ArgumentCaptor<CheckInRecord> checkInRecordCaptor;

    @Test
    public void shouldReturnCheckinRecord() throws Exception {

        CheckInRecord checkInRecord = constructCheckinRecord();
        CheckInRecordDTO checkInRecordDTO = constructCheckinRecordDTO();

        when(checkinRepository.findOne(CHECK_IN_ID)).thenReturn(checkInRecord);
        when(checkInMapper.map(checkInRecord, CheckInRecordDTO.class)).thenReturn(checkInRecordDTO);

        CheckInRecordDTO actual = checkinService.getCheckInRecord(CHECK_IN_ID);

        assertEquals(checkInRecordDTO, actual);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFound() throws Exception {

        when(checkinRepository.findOne(CHECK_IN_ID)).thenReturn(null);

        checkinService.getCheckInRecord(CHECK_IN_ID);
    }

    @Test
    public void shouldCheckIn() throws Exception {

        CheckInRecord checkInRecord = constructCheckinRecord();
        CheckInRecordDTO checkInRecordDTO = constructCheckinRecordDTO();


        when(checkInMapper.map(checkInRecordDTO, CheckInRecord.class)).thenReturn(checkInRecord);

        checkInRecord.setId(CHECK_IN_ID);
        when(checkinRepository.save(checkInRecordCaptor.capture())).thenReturn(checkInRecord);

        Long savedCheckInId = checkinService.checkIn(checkInRecordDTO);

        assertEquals(CHECK_IN_ID, savedCheckInId);
        assertNotNull(checkInRecordCaptor.getValue().getCheckInTime());

        verify(sender).send(CHECK_IN_ID);
    }
}