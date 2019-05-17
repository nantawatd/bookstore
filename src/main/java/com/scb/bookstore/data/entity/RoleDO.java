package com.scb.bookstore.data.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String role;

	@OneToMany(mappedBy = "roleDO")
	private Set<UserDO> userDOs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UserDO> getUserDOs() {
		return userDOs;
	}

	public void setUserDOs(Set<UserDO> userDOs) {
		this.userDOs = userDOs;
	}
}