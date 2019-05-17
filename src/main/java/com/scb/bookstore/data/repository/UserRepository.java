package com.scb.bookstore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scb.bookstore.data.entity.UserDO;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Long>{
	UserDO findByUsername(String username);
	Long countByUsername(String username);
}
