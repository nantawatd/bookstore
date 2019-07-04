package com.scb.bookstore.feature.book.service;

import java.util.List;

import com.scb.bookstore.exception.BookstoreException;
import com.scb.bookstore.feature.book.dto.response.SCBBookTO;

public interface BookService {
	public List<SCBBookTO> getBooks() throws BookstoreException;
}
