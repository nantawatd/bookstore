package com.scb.bookstore.data.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_order")
public class OrderDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDO userDO;

	@OneToMany(mappedBy = "orderDO")
	private Set<OrderBookDO> orderBookDOs;

	private Double price;

	public OrderDO() {

	}

	public OrderDO(UserDO userDO, Date createdDate, Double price) {
		this.userDO = userDO;
		this.createdDate = createdDate;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public Set<OrderBookDO> getOrderBookDOs() {
		return orderBookDOs;
	}

	public void setOrderBookDOs(Set<OrderBookDO> orderBookDOs) {
		this.orderBookDOs = orderBookDOs;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
