package com.scb.bookstore.service.exception;

public class BookstoreErrorMessage {
	public static final String USER_DUPLICATE = "Username is duplicated.";
	public static final String USER_NOT_FOUND = "User not found.";
	public static final String USER_INVALID = "User is invalid.";
	public static final String EMPTY_BOOK_LIST = "Please get book list before creating an order.";

	private BookstoreErrorMessage() {
		throw new IllegalStateException("Error Message class");
	}
}
