package com.scb.bookstore.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scb.bookstore.config.CachingConfig;
import com.scb.bookstore.config.model.BookStoreUserDetails;
import com.scb.bookstore.data.entity.OrderBookDO;
import com.scb.bookstore.data.entity.OrderDO;
import com.scb.bookstore.data.entity.UserDO;
import com.scb.bookstore.data.repository.OrderBookRepository;
import com.scb.bookstore.data.repository.OrderRepository;
import com.scb.bookstore.data.repository.RoleRepository;
import com.scb.bookstore.data.repository.UserRepository;
import com.scb.bookstore.rest.dto.Role;
import com.scb.bookstore.rest.dto.request.UserOrderTO;
import com.scb.bookstore.rest.dto.request.UserTO;
import com.scb.bookstore.rest.dto.response.OrderPriceTO;
import com.scb.bookstore.rest.dto.response.SCBBookTO;
import com.scb.bookstore.service.UserService;
import com.scb.bookstore.service.exception.BookstoreErrorMessage;
import com.scb.bookstore.service.exception.BookstoreException;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderBookRepository orderBookRepository;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserTO getUserById(Long id) throws BookstoreException {
		LOG.info("get user ID : {} ", id);

		LOG.info("validate user ID");
		validateUserId(id);

		Optional<UserDO> userDO = userRepository.findById(id);
		if (userDO.isPresent()) {
			return userDO.get().toUserTO();
		} else {
			throw new BookstoreException(BookstoreErrorMessage.USER_NOT_FOUND);
		}
	}

	@Override
	public void createUser(UserTO user) throws BookstoreException {
		LOG.info("create user account.");

		if(userRepository.countByUsername(user.getUsername()) > 0) {
			throw new BookstoreException(BookstoreErrorMessage.USER_DUPLICATE);
		}

		//encode password.
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserDO userDO = UserDO.from(user);
		userDO.setRoleDO(roleRepository.findByRole(Role.ROLE_CUSTOMER.name()));

		LOG.info("save user");
		userRepository.save(userDO);
	}

	@Override
	public void deleteUser() throws BookstoreException {
		LOG.info("remove the user account.");

		Optional<UserDO> userDO = getUserByAuthentication();
		if (userDO.isPresent()) {
			LOG.info("delete user");
			userRepository.delete(userDO.get());
		} else {
			throw new BookstoreException(BookstoreErrorMessage.USER_NOT_FOUND);
		}

		LOG.info("log out");
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Override
	public OrderPriceTO orderBook(UserOrderTO order) throws BookstoreException {
		Optional<UserDO> userDO = getUserByAuthentication();
		if (userDO.isPresent()) {

			double totalPrice = getTotalPriceFromBookID(order.getBookIds());
			LOG.info("total price : {}", totalPrice);

			LOG.info("save order.");
			OrderDO orderDO = new OrderDO(userDO.get(), new Date(), totalPrice);
			orderDO = orderRepository.save(orderDO);
			Long orderId = orderDO.getId();

			LOG.info("save customer order.");
			order.getBookIds().forEach(bookId -> {
				OrderBookDO orderBookDO = new OrderBookDO(bookId, orderId);
				orderBookRepository.save(orderBookDO);
			});

			return new OrderPriceTO(totalPrice);

		} else {
			throw new BookstoreException(BookstoreErrorMessage.USER_NOT_FOUND);
		}
	}

	private double getTotalPriceFromBookID(List<Long> bookIds) throws BookstoreException {
		Cache cache = cacheManager.getCache(CachingConfig.BOOK_CACHE);
		ValueWrapper bookWrapper = cache.get(CachingConfig.BOOK_CACHE_KEY);

		if(bookWrapper == null) {
			throw new BookstoreException(BookstoreErrorMessage.EMPTY_BOOK_LIST);
		}

		@SuppressWarnings("unchecked")
		List<SCBBookTO> scbBooks = (List<SCBBookTO>) bookWrapper.get();

		return scbBooks.stream()
		.filter(book -> bookIds.contains(book.getId()))
		.map(SCBBookTO::getPrice)
		.mapToDouble(Double::doubleValue).sum();
	}

	private void validateUserId(Long id) throws BookstoreException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Long userId = ((BookStoreUserDetails) auth.getPrincipal()).getUserId();
			if(userId != id) {
				throw new BookstoreException(BookstoreErrorMessage.USER_INVALID);
			}
		} else {
			throw new BookstoreException(BookstoreErrorMessage.USER_NOT_FOUND);
		}
	}

	private Optional<UserDO> getUserByAuthentication() throws BookstoreException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Long userId = ((BookStoreUserDetails) auth.getPrincipal()).getUserId();
			Optional<UserDO> userDO = userRepository.findById(userId);
			return userDO;
		} else {
			throw new BookstoreException(BookstoreErrorMessage.USER_NOT_FOUND);
		}
	}

}
