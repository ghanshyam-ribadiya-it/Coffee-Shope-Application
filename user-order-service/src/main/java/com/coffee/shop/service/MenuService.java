package com.coffee.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.domain.Menu;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.model.MenuDetail;
import com.coffee.shop.model.MenuListResponse;
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
	
	public MenuListResponse findAllMenuByCoffeeShopId(Long coffeeShopId) {
		List<Menu> menuList = menuRepository.findAllMenuByCoffeeShopId(coffeeShopId);
		
		List<MenuDetail> menuDetails = new ArrayList<>(menuList.size());
		for(Menu menu : menuList) {
			menuDetails.add(new MenuDetail(menu.getId(), menu.getItemName(), menu.getPrice()));
		}
		
		MenuListResponse menuListResponse = new MenuListResponse();
		menuListResponse.setMenuDetails(menuDetails);
		return menuListResponse;
	}
}
