package com.scb.bookstore.feature.book.service.impl;

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
import org.springframework.web.client.RestTemplate;

import com.scb.bookstore.config.CachingConfig;
import com.scb.bookstore.exception.BookstoreErrorMessage;
import com.scb.bookstore.exception.BookstoreException;
import com.scb.bookstore.feature.book.dto.response.SCBBookTO;
import com.scb.bookstore.feature.book.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Value("${scb.book.all.url}")
	private String allBooksUrl;

	@Value("${scb.book.recommend.url}")
	private String recommendBooksUrl;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

	@Cacheable(cacheNames = CachingConfig.BOOK_CACHE, key = "'" + CachingConfig.BOOK_CACHE_KEY + "'")
	@Override
	public List<SCBBookTO> getBooks() throws BookstoreException {
		try {
			// get all books
			List<SCBBookTO> scbAllBooks = getBooksFromSCBByUrl(allBooksUrl);

			// get recommend books
			List<SCBBookTO> scbRecommendBooks = getBooksFromSCBByUrl(recommendBooksUrl);

			scbAllBooks = scbAllBooks.stream().map(book -> {
				scbRecommendBooks.parallelStream().forEach(rec -> {
					if(rec.getId() == book.getId()) {
						book.setRecommended(true);
					}
				});
				return book;
			}).collect(Collectors.toList());

			return scbAllBooks;

		} catch (Exception e) {
			throw new BookstoreException(BookstoreErrorMessage.FAIL_TO_CALL_SCB_ENDPOINT);
		}
	}

	private List<SCBBookTO> getBooksFromSCBByUrl(String scbBookUrl) {
		LOG.info("call API {}", scbBookUrl);
		return restTemplate.exchange(scbBookUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<SCBBookTO>>() {}).getBody();
	}
}
