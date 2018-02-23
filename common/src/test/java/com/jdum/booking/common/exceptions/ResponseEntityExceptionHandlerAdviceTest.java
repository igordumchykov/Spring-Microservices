package com.jdum.booking.common.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.WebRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author idumchykov
 * @since 2/23/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ResponseEntityExceptionHandlerAdviceTest {

    private static final int ID = 1;

    @InjectMocks
    private ResponseEntityExceptionHandlerAdvice controllerAdvice;

    @Mock
    private WebRequest webRequest;

    @Test
    public void shouldHandleNotFoundException() throws Exception {

        String details = "Details";
        String exceptionMessage = String.format("BookingRecord for id %d not found", 1);
        NotFoundException exception = new NotFoundException(exceptionMessage);

        when(webRequest.getDescription(false)).thenReturn(details);

        ResponseEntity<ErrorDetails> result = controllerAdvice.handle(exception, webRequest);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        ErrorDetails errorDetails = result.getBody();
        assertNotNull(errorDetails.getTimestamp());
        assertEquals(exceptionMessage, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

    @Test
    public void shouldHandleBusinessServiceException() throws Exception {

        String details = "Details";
        String exceptionMessage = "No more seats available";
        BusinessServiceException exception = new BusinessServiceException(exceptionMessage);

        when(webRequest.getDescription(false)).thenReturn(details);

        ResponseEntity<ErrorDetails> result = controllerAdvice.handle(exception, webRequest);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        ErrorDetails errorDetails = result.getBody();
        assertNotNull(errorDetails.getTimestamp());
        assertEquals(exceptionMessage, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }
}