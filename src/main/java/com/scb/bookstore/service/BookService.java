package com.scb.bookstore.service;

import java.util.List;

import com.scb.bookstore.rest.dto.response.SCBBookTO;

public interface BookService {
	public List<SCBBookTO> getBooks();
}
