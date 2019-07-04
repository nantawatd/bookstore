package com.scb.bookstore.exception;

public class BookstoreException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	public BookstoreException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public BookstoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookstoreException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BookstoreException(String message) {
		super(message);
	}

	public String getErrorCode() {
		return errorCode;
	}
}
