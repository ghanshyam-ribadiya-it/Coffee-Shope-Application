package com.coffee.shop.controller;

import com.coffee.shop.model.CoffeeShopDetailResponse;
import com.coffee.shop.model.CoffeeShopListResponse;
import com.coffee.shop.model.MenuListResponse;
import com.coffee.shop.model.MenuResponse;
import com.coffee.shop.service.CoffeeShopService;
import com.coffee.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffeeShop")
public class CoffeeShopController {

    @Autowired
    private CoffeeShopService coffeeShopService;

    @Autowired
    private MenuService menuService;

    @GetMapping("{coffeeShopId}")
    public CoffeeShopDetailResponse getCoffeeShopDetailById(@PathVariable Long coffeeShopId) {
        return coffeeShopService.getCoffeeShopById(coffeeShopId);
    }

    @GetMapping("/search/areaCode/{areaCode}")
    public CoffeeShopListResponse findAllCoffeeShopByAreaCode(@PathVariable String areaCode) {
        return coffeeShopService.findAllCoffeeShopByAreaCode(areaCode);
    }

    @GetMapping("/{coffeeShopId}/menu")
    public MenuListResponse findAllMenuItem(@PathVariable Long coffeeShopId) {
        return menuService.findAllMenuByCoffeeShopId(coffeeShopId);
    }

    @GetMapping("/{coffeeShopId}/menu/{menuId}")
    public MenuResponse getMenuDetailByIds(@PathVariable Long coffeeShopId, @PathVariable Long menuId) {
        return menuService.getMenuByIds(coffeeShopId, menuId);
    }
}
