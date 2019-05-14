package com.scb.bookstore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scb.bookstore.config.model.BookStoreUserDetails;
import com.scb.bookstore.data.entity.UserDO;
import com.scb.bookstore.data.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDO userDO = userRepository.findByUsername(username);

		if(userDO == null) {
			throw new UsernameNotFoundException("User not found.");
		}

		// set role
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority(userDO.getRoleDO().getRole()));

		// before comparing password, if password is encoded in db, just send password here.
		return new BookStoreUserDetails(username, userDO.getPassword(), userDO.getId(), list);
	}
}
