package com.scb.bookstore.feature.user.service;

import com.scb.bookstore.exception.BookstoreException;
import com.scb.bookstore.feature.book.dto.response.OrderPriceTO;
import com.scb.bookstore.feature.user.dto.request.UserOrderTO;
import com.scb.bookstore.feature.user.dto.request.UserTO;

public interface UserService {
	public UserTO getUserById(Long id) throws BookstoreException;
	public void createUser(UserTO user) throws BookstoreException;
	public void deleteUser() throws BookstoreException;
	public OrderPriceTO orderBook(UserOrderTO order) throws BookstoreException;
}
