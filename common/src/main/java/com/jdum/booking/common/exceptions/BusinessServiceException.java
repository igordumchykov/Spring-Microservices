package com.jdum.booking.common.exceptions;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessServiceException extends RuntimeException  {
	
	public BusinessServiceException(String message){
		super(message);
	}
}
