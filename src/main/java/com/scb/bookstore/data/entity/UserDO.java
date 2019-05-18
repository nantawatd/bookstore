package com.scb.bookstore.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.scb.bookstore.rest.dto.request.UserTO;

@Entity
@Table(name = "user")
public class UserDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	private String password;
	private String name;
	private String surname;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "date_created")
	private Date dateCreated;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleDO roleDO;

	@OneToMany(mappedBy = "userDO", cascade = CascadeType.ALL)
	private Set<OrderDO> orderDOs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public RoleDO getRoleDO() {
		return roleDO;
	}

	public void setRoleDO(RoleDO roleDO) {
		this.roleDO = roleDO;
	}

	public static UserDO from(UserTO userTO) {
		UserDO userDO = new UserDO();
		userDO.setUsername(userTO.getUsername());
		userDO.setPassword(userTO.getPassword());
		userDO.setName(userTO.getName());
		userDO.setSurname(userTO.getSurname());
		userDO.setDateOfBirth(userTO.getDateOfBirth());
		userDO.setDateCreated(new Date());
		return userDO;
	}

	public UserTO toUserTO() {
		UserTO userTO = new UserTO();
		userTO.setName(name);
		userTO.setSurname(surname);
		userTO.setDateOfBirth(dateOfBirth);
		userTO.setUsername(username);
		userTO.setId(id);

		if(orderDOs != null) {
			List<Long> bookIds = new ArrayList<>();
			orderDOs.forEach(orderDo ->
				orderDo.getOrderBookDOs().forEach(orderBook -> {
					bookIds.add(orderBook.getId().getBookID());
				})
			);
			userTO.setBookIds(bookIds);
		}

		return userTO;
	}

	public Set<OrderDO> getOrderDOs() {
		return orderDOs;
	}

	public void setOrderDOs(Set<OrderDO> orderDOs) {
		this.orderDOs = orderDOs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}