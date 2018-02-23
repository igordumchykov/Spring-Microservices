package com.jdum.booking.common.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author idumchykov
 * @since 2/21/18
 */
@Slf4j
@ControllerAdvice
public class ResponseEntityExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handle(NotFoundException e, WebRequest request) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(BusinessServiceException.class)
    public ResponseEntity<ErrorDetails> handle(BusinessServiceException e, WebRequest request) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false)));
    }
}
