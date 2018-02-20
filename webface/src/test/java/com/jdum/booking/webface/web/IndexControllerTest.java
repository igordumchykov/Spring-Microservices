package com.jdum.booking.webface.web;

import com.jdum.booking.common.dto.SearchQuery;
import com.jdum.booking.webface.dto.UIData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jdum.booking.webface.web.IndexController.SEARCH_VIEW;
import static com.jdum.booking.webface.web.IndexController.UIDATA_ATTRIBUTE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author idumchykov
 * @since 2/14/18
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCorrectView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute(UIDATA_ATTRIBUTE, new UIData(SearchQuery.getDefault())))
                .andExpect(view().name(SEARCH_VIEW));
    }

}