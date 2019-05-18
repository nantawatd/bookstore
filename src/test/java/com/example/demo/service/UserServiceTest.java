package com.example.demo.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scb.bookstore.config.model.BookStoreUserDetails;
import com.scb.bookstore.data.entity.RoleDO;
import com.scb.bookstore.data.entity.UserDO;
import com.scb.bookstore.data.repository.RoleRepository;
import com.scb.bookstore.data.repository.UserRepository;
import com.scb.bookstore.rest.dto.request.UserTO;
import com.scb.bookstore.service.exception.BookstoreErrorMessage;
import com.scb.bookstore.service.exception.BookstoreException;
import com.scb.bookstore.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final String NAME = "nantawat";
	private static final String SURNAME = "dejcharoen";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String ROLE = "ROLE_USER";
	private static final Long USER_ID = 1L;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	RoleRepository roleRepository;

	@Mock
	Authentication authentication;

	@Mock
	SecurityContext securityContext;

	@Mock
	PasswordEncoder passwordEncoder;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void getUserById() throws BookstoreException {

		// given
		Optional<UserDO> returnValue = createUserDO();
		mockAuthentication();
		Mockito.when(userRepository.findById(USER_ID)).thenReturn(returnValue);

		// when
		UserTO userTO = userServiceImpl.getUserById(1L);

		// then
		assertTrue(userTO.getId().equals(USER_ID));
	}

	@Test
	public void createUser() throws BookstoreException {

		// given
		when(userRepository.countByUsername(USERNAME)).thenReturn(0L);
		when(roleRepository.findByRole(ArgumentMatchers.anyString())).thenReturn(createRoleDO());
		when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
		UserTO userTO = createUserTO();

		// when
		userServiceImpl.createUser(userTO);
	}

	@Test
	public void createUserWhenUsernameDuplicated() throws BookstoreException {
		// expect
		expectedEx.expect(BookstoreException.class);
	    expectedEx.expectMessage(BookstoreErrorMessage.USER_DUPLICATE);

	    // given
		when(userRepository.countByUsername(USERNAME)).thenReturn(1L);
		UserTO userTO = createUserTO();

		// when
		userServiceImpl.createUser(userTO);
	}

	private void mockAuthentication() {
		SecurityContextHolder.setContext(securityContext);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority(ROLE));
		BookStoreUserDetails bookStoreUserDetails = new BookStoreUserDetails(USERNAME, PASSWORD, 1L, list);

		Mockito.when(authentication.getPrincipal()).thenReturn(bookStoreUserDetails);
	}

	private Optional<UserDO> createUserDO() {
		UserDO userDO = new UserDO();
		userDO.setId(USER_ID);
		userDO.setUsername(USERNAME);
		userDO.setName(NAME);
		Optional<UserDO> returnValue = Optional.of(userDO);
		return returnValue;
	}

	private RoleDO createRoleDO() {
		RoleDO roleDO = new RoleDO();
		roleDO.setRole(ROLE);
		return roleDO;
	}

	private UserTO createUserTO() {
		UserTO userTO = new UserTO();
		userTO.setName(NAME);
		userTO.setUsername(USERNAME);
		userTO.setSurname(SURNAME);
		userTO.setPassword(PASSWORD);
		return userTO;
	}
}
