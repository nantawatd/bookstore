package com.scb.bookstore.service;

import com.scb.bookstore.rest.dto.request.UserOrderTO;
import com.scb.bookstore.rest.dto.request.UserTO;
import com.scb.bookstore.rest.dto.response.OrderPriceTO;
import com.scb.bookstore.service.exception.BookstoreException;

public interface UserService {
	public UserTO getUserById(Long id) throws BookstoreException;
	public void createUser(UserTO user) throws BookstoreException;
	public void deleteUser() throws BookstoreException;
	public OrderPriceTO orderBook(UserOrderTO order) throws BookstoreException;
}
