package com.coffee.shop.service;

import com.coffee.shop.model.CoffeeShopDetailResponse;
import com.coffee.shop.model.MenuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CoffeeShopService {

    @Autowired
    private RestTemplate restTemplate;

    public CoffeeShopDetailResponse getCoffeeShopDetailById(Long coffeeShopId) {
        log.info("CoffeeShop API is going to call for the coffeeShopId : {}", coffeeShopId);

        CoffeeShopDetailResponse coffeeShopDetailResponse = restTemplate.getForObject("http://COFFEE-SHOP-SERVICE/coffeeShop/" + coffeeShopId, CoffeeShopDetailResponse.class);
        log.info("CoffeeShop API response code : {}", coffeeShopDetailResponse.getResultCode());

        return coffeeShopDetailResponse;
    }

    public MenuResponse getMenuDetailsByIds(Long coffeeShopId, Long menuId) {
        log.info("CoffeeShop API is going to call for the coffeeShopId : {}, menuId : {}", coffeeShopId, menuId);

        MenuResponse menuResponse = restTemplate.getForObject("http://COFFEE-SHOP-SERVICE/coffeeShop/" + coffeeShopId + "/menu/" + menuId, MenuResponse.class);
        log.info("CoffeeShop API response code : {}", menuResponse.getResultCode());

        return menuResponse;
    }
}
