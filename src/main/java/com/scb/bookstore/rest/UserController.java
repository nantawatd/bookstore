package com.scb.bookstore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.bookstore.rest.dto.UserOrderTO;
import com.scb.bookstore.rest.dto.UserTO;
import com.scb.bookstore.service.BookService;
import com.scb.bookstore.service.UserService;
import com.scb.bookstore.service.exception.BookstoreException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="User Management System")
@RestController
public class UserController {

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@ApiOperation(value = "Create user", response = Void.class)
	@PostMapping("/user")
	public void createUser(@RequestBody UserTO user) {
		userService.createUser(user);
	}

	@ApiOperation(value = "Delete user", response = Void.class)
	@DeleteMapping("/user")
	public void deleteUser() throws BookstoreException {
		userService.deleteUser();
	}

	@ApiOperation(value = "Order books", response = Void.class)
	@PostMapping("/user/orders")
	public void orderBook(UserOrderTO order) throws BookstoreException {
		userService.orderBook(order);
	}

	@ApiOperation(value = "View a user by ID", response = UserTO.class)
	@GetMapping("/user/{id}")
	public UserTO getUserById(@PathVariable(name = "id") Long id) throws BookstoreException {
		return userService.getUserById(id);
	}
}
