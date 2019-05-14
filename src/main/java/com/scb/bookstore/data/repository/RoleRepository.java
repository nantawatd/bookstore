package com.scb.bookstore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scb.bookstore.data.entity.RoleDO;

@Repository
public interface RoleRepository extends CrudRepository<RoleDO, Long>{
	RoleDO findByRole(String role);
}
