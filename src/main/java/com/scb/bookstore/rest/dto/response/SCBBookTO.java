package com.scb.bookstore.rest.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SCBBookTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private double price;

	@JsonProperty("book_name")
	private String bookName;

	@JsonProperty("author_name")
	private String authorName;

	@JsonProperty("is_recommended")
	private boolean isRecommended;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
}
