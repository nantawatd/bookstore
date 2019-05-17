package com.scb.bookstore.rest.dto.response;

import java.io.Serializable;

public class OrderPriceTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private double price;

	public OrderPriceTO() {}

	public OrderPriceTO(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
