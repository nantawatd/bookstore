package com.scb.bookstore.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public BookstoreException handleGeneralException(Exception e) {
		BookstoreException bookstoreException = new BookstoreException("500", "General Error.");
		return bookstoreException;
	}
	
	@ExceptionHandler(BookstoreException.class)
	public BookstoreException handleBookstoreException(BookstoreException e) {
		BookstoreException bookstoreException = new BookstoreException(e.getErrorCode(), "TEST");
		return bookstoreException;
	}
}
