package com.scb.bookstore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scb.bookstore.data.entity.OrderDO;

@Repository
public interface OrderRepository extends CrudRepository<OrderDO, Long>{
}
