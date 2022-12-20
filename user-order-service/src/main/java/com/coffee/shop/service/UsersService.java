package com.coffee.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.domain.Users;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public Users getUserById(Long userId) {
		log.info("Find User by userId : {}", userId);
		
		Optional<Users> users = usersRepository.findById(userId);
		if (users.isPresent()) {
			return users.get();
		}
		
		throw new ShopException(ErrorAppMessage.USER_NOT_FOUND);
	}
}