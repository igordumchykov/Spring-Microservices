package com.jdum.booking.search.service;

import com.jdum.booking.search.repository.TripRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * @author idumchykov
 * @since 2/14/18
 */
public class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private TripRepository tripRepository;

    @Test
    public void test() throws Exception {
//        assertTrue(false);
    }
}