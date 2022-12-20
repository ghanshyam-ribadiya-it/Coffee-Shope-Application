package com.coffee.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.domain.UserHasCoffeeShop;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.repository.UserHasCoffeeShopRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoffeeShopService {
	
	@Autowired
	private UserHasCoffeeShopRepository userHasCoffeeShopRepository;

	public UserHasCoffeeShop getCoffeeShopById(Long coffeeShopId) {
		log.info("Find CoffeeShop by coffeeShopId : {}", coffeeShopId);
		
		Optional<UserHasCoffeeShop> coffeeShop = userHasCoffeeShopRepository.findById(coffeeShopId);
		if (coffeeShop.isPresent()) {
			return coffeeShop.get();
		}
		
		throw new ShopException(ErrorAppMessage.COFFEE_SHOP_NOT_FOUND);
	}

}
