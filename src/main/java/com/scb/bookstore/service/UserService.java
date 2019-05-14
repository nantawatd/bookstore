package com.scb.bookstore.service;

import com.scb.bookstore.rest.dto.UserTO;
import com.scb.bookstore.service.exception.BookstoreException;

public interface UserService {
	public UserTO getUserById(Long id) throws BookstoreException;
	public void createUser(UserTO user);
	public void deleteUser() throws BookstoreException;
}
