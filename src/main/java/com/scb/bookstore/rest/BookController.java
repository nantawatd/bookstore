package com.scb.bookstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.bookstore.rest.dto.response.SCBBookTO;
import com.scb.bookstore.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Book Management System")
@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	PasswordEncoder passwordEncorder;

	@ApiOperation(value = "View a list of available books from SCB", response = List.class)
	@GetMapping("/books")
	public List<SCBBookTO> getBooks() {
		return bookService.getBooks();
	}
}
