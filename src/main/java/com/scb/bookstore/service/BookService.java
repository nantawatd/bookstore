package com.scb.bookstore.service;

import java.util.List;

import com.scb.bookstore.rest.dto.response.SCBBookTO;
import com.scb.bookstore.service.exception.BookstoreException;

public interface BookService {
	public List<SCBBookTO> getBooks() throws BookstoreException;
}
