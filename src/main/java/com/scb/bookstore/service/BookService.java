package com.scb.bookstore.service;

import java.util.List;

import com.scb.bookstore.rest.dto.SCBBook;

public interface BookService {
	public List<SCBBook> getBooks();
}
