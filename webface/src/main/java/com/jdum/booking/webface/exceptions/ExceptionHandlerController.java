package com.jdum.booking.webface.exceptions;

import com.jdum.booking.common.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import static com.jdum.booking.common.rest.RestConstants.NOT_FOUND_MODEL_NAME;
import static com.jdum.booking.common.rest.RestConstants.NOT_FOUND_VIEW_NAME;

/**
 * @author idumchykov
 * @since 2/19/18
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ModelAndView handleNotFound(Exception exception) {

        log.error("Handling NotFoundException");
        log.error(exception.getMessage());

        return new ModelAndView(NOT_FOUND_VIEW_NAME, NOT_FOUND_MODEL_NAME, exception);
    }
}
