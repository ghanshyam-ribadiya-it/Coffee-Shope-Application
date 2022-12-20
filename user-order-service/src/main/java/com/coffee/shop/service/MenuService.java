package com.coffee.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.domain.Menu;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.repository.MenuRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public Menu getMenuById(Long menuId) {
		log.info("Find Menu by menuId : {}", menuId);

		Optional<Menu> menu = menuRepository.findById(menuId);
		if (menu.isPresent()) {
			return menu.get();
		}

		throw new ShopException(ErrorAppMessage.MENU_NOT_FOUND);
	}
}
