package com.scb.bookstore.service.exception;

public class BookstoreErrorMessage {
	public static final String USER_NOT_FOUND = "User not found.";
	public static final String USER_INVALID = "User is invalid.";

	private BookstoreErrorMessage() {
		throw new IllegalStateException("Error Message class");
	}
}
