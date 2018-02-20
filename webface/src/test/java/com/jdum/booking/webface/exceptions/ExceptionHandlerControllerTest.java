package com.jdum.booking.webface.exceptions;

import com.jdum.booking.common.exceptions.NotFoundException;
import com.jdum.booking.common.rest.RestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author idumchykov
 * @since 2/19/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ExceptionHandlerControllerTest {

    @SpyBean
    private ExceptionHandlerController exceptionHandlerController;

    @Test
    public void shouldReturn404View() throws Exception {
        Exception exception = new NotFoundException("Data not found");
        ModelAndView modelAndView = exceptionHandlerController.handleNotFound(exception);

        assertNotNull(modelAndView);
        assertEquals(RestConstants.NOT_FOUND_VIEW_NAME, modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get(RestConstants.NOT_FOUND_MODEL_NAME));
        assertEquals(exception, modelAndView.getModel().get(RestConstants.NOT_FOUND_MODEL_NAME));
    }
}