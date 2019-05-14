package com.scb.bookstore.config.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class BookStoreUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private Long userId;

	public BookStoreUserDetails(String username, String password, Long userId, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
