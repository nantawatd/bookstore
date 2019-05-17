package com.scb.bookstore.data.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_book")
public class OrderBookDO {

	@EmbeddedId
	private OrderBookDOPK id;

	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private OrderDO orderDO;

//	@ManyToOne
//	@JoinColumn(name = "book_id", insertable = false, updatable = false)
//	private BookDO bookDO;

	public OrderBookDO() {}

	public OrderBookDO(Long bookID, Long orderID) {
		this.id = new OrderBookDOPK(bookID, orderID);
	}

	public OrderBookDOPK getId() {
		return id;
	}

	public void setId(OrderBookDOPK id) {
		this.id = id;
	}

	public OrderDO getOrderDO() {
		return orderDO;
	}

	public void setOrderDO(OrderDO orderDO) {
		this.orderDO = orderDO;
	}

}
