/*package com.scb.bookstore.data.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookDO {

	@Id
	private Long id;
	private String name;
	private String author;
	private String price;

	@OneToMany(mappedBy = "bookDO")
	private Set<OrderBookDO> orderBookDOs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Set<OrderBookDO> getOrderBookDOs() {
		return orderBookDOs;
	}

	public void setOrderBookDOs(Set<OrderBookDO> orderBookDOs) {
		this.orderBookDOs = orderBookDOs;
	}
}
*/