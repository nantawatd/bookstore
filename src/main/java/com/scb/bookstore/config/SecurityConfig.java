package com.scb.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		//.antMatchers("**").permitAll()
        .antMatchers("/swagger-ui.html/**").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/v2/api-docs/**").permitAll()
        .antMatchers("/swagger.json").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/nantawat/**").permitAll()

		.antMatchers("/welcome").permitAll()
		.antMatchers(HttpMethod.POST,"/user").permitAll()
		.antMatchers("/user/**").hasAnyRole("CUSTOMER", "ADMIN")
		.antMatchers("/book/**").hasAnyRole("CUSTOMER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/books").permitAll()

		.anyRequest().authenticated().and()
		.formLogin().permitAll().and().logout().permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

		// basic authentication.
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() { // we can choose either to use spring DaoAuthenticationProvider
		// or custom UserRepositoryAuthenticationProvider implements AuthenticationProvider (like STS)

		DaoAuthenticationProvider authenProvider = new DaoAuthenticationProvider();
		authenProvider.setUserDetailsService(userDetailsService);
		authenProvider.setPasswordEncoder(passwordEncoder());
		return authenProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
