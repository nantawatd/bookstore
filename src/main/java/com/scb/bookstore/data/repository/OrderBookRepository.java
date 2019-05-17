package com.scb.bookstore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scb.bookstore.data.entity.OrderBookDO;
import com.scb.bookstore.data.entity.OrderBookDOPK;

@Repository
public interface OrderBookRepository extends CrudRepository<OrderBookDO, OrderBookDOPK>{
}
