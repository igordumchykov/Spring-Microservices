package com.jdum.booking.webface.controller;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.webface.dto.UIData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author idumchykov
 * @since 2/14/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTest {

    private static final String UIDATA_ATTRIBUTE = "uidata";
    private static final String SEARCH_VIEW = "search";

    @InjectMocks
    private IndexController indexController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
            mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void shouldReturnCorrectView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault())))
                .andExpect(view().name(SEARCH_VIEW));
    }

}