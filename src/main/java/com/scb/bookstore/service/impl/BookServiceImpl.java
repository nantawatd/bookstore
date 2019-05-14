package com.scb.bookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.scb.bookstore.rest.dto.SCBBook;
import com.scb.bookstore.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService{

	@Value("${scb.book.all.url}")
	private String allBooksUrl;

	@Value("${scb.book.recommend.url}")
	private String recommendBooksUrl;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

	@Cacheable("books")
	@Override
	public List<SCBBook> getBooks() {
		// get all books
		List<SCBBook> scbAllBooks = getBooksFromSCBByUrl(allBooksUrl);

		// get recommend books
		List<SCBBook> scbRecommendBooks = getBooksFromSCBByUrl(recommendBooksUrl);

		scbAllBooks = scbAllBooks.stream().map(book -> {
			scbRecommendBooks.parallelStream().forEach(rec -> {
				if(rec.getId() == book.getId()) {
					book.setRecommended(true);
				}
			});
			return book;
		}).collect(Collectors.toList());

		return scbAllBooks;
	}

	private List<SCBBook> getBooksFromSCBByUrl(String scbBookUrl) {
		LOG.info("call API {}", scbBookUrl);
		return restTemplate.exchange(scbBookUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<SCBBook>>() {}).getBody();
	}
}
