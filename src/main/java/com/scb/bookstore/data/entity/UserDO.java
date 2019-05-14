package com.scb.bookstore.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.scb.bookstore.rest.dto.UserTO;

@Entity
@Table(name = "user")
public class UserDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	private String password;
	private String alias;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "date_created")
	private Date dateCreated;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleDO roleDO;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
		userDO.setAlias(userTO.getAlias());
		userDO.setDateOfBirth(userTO.getDateOfBirth());
		userDO.setDateCreated(new Date());
		return userDO;
	}

	public UserTO toUserTO() {
		UserTO userTO = new UserTO();
		userTO.setAlias(alias);
		userTO.setDateOfBirth(dateOfBirth);
		userTO.setUsername(username);
		userTO.setId(id);
		return userTO;
	}
}