package com.scb.bookstore.rest.dto;

import java.util.List;

public class UserOrderTO {
	private List<Long> bookIds;

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}
}
