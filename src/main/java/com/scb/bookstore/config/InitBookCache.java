package com.scb.bookstore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.scb.bookstore.exception.BookstoreException;
import com.scb.bookstore.feature.book.service.BookService;

@Component
public class InitBookCache implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private BookService bookService;

	private static final Logger LOG = LoggerFactory.getLogger(InitBookCache.class);

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		LOG.info("initial book data.");
		try {
			bookService.getBooks();
		} catch (BookstoreException e) {
			LOG.error(e.getMessage());
		}
	}
}
