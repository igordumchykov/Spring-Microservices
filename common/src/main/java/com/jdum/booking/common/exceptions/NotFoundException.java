package com.jdum.booking.common.exceptions;

/**
 * @author idumchykov
 * @since 2/19/18
 */
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
