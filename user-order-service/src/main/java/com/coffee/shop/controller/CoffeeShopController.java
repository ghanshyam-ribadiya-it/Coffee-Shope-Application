package com.coffee.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.shop.model.MenuListResponse;
import com.coffee.shop.service.MenuService;

@RestController
@RequestMapping("/coffeeShop")
public class CoffeeShopController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/{id}/searchMenu")
	public ResponseEntity<MenuListResponse> findAllMenuItem(@PathVariable Long id) {
		return ResponseEntity.ok(menuService.findAllMenuByCoffeeShopId(id));
	}
}
