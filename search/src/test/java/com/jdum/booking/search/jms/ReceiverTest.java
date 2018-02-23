package com.jdum.booking.search.jms;

import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.search.util.TestDataCreator.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * @author idumchykov
 * @since 2/23/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ReceiverTest {

    @InjectMocks
    private Receiver receiver;

    @Mock
    private SearchService searchService;

    @Test
    public void shouldProcessMessage() throws Exception {

        doNothing().when(searchService).updateInventory(BUS_NUMBER, TRIP_DATE, INVENTORY_COUNT);

        receiver.processMessage(constructMessage());

        verify(searchService).updateInventory(BUS_NUMBER, TRIP_DATE, INVENTORY_COUNT);
    }

    @Test
    public void shouldNotThrowNotFoundIfSearchServiceThrowsNotFound() throws Exception {

        doThrow(NotFoundException.class).when(searchService).updateInventory(BUS_NUMBER, TRIP_DATE, INVENTORY_COUNT);

        receiver.processMessage(constructMessage());

        verify(searchService).updateInventory(BUS_NUMBER, TRIP_DATE, INVENTORY_COUNT);
    }
}