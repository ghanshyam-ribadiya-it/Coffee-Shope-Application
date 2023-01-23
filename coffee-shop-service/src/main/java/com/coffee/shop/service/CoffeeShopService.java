package com.coffee.shop.service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.constants.SuccessAppMessage;
import com.coffee.shop.domain.CoffeeShop;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.model.CoffeeShopDetail;
import com.coffee.shop.model.CoffeeShopDetailResponse;
import com.coffee.shop.model.CoffeeShopListResponse;
import com.coffee.shop.repository.CoffeeShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CoffeeShopService {

    @Autowired
    private CoffeeShopRepository coffeeShopRepository;

    @Transactional
    public CoffeeShopDetailResponse getCoffeeShopById(Long coffeeShopId) {
        log.info("Find CoffeeShop by coffeeShopId : {}", coffeeShopId);

        Optional<CoffeeShop> coffeeShop = coffeeShopRepository.findById(coffeeShopId);
        if (coffeeShop.isPresent()) {
            CoffeeShopDetailResponse coffeeShopDetailResponse = new CoffeeShopDetailResponse(SuccessAppMessage.OK_Coffee_Shop_Details);
            coffeeShopDetailResponse.setCoffeeShopDetail(new CoffeeShopDetail(coffeeShop.get().getId(), coffeeShop.get().getName()));
            return coffeeShopDetailResponse;
        }

        throw new ShopException(ErrorAppMessage.COFFEE_SHOP_NOT_FOUND);
    }

    @Transactional
    public CoffeeShopListResponse findAllCoffeeShopByAreaCode(String areaCode) {
        log.info("Find CoffeeShop by areaCode : {}", areaCode);

        List<CoffeeShop> coffeeShopList = coffeeShopRepository.findByAreaCode(areaCode);

        List<CoffeeShopDetail> coffeeShopDetails = new ArrayList<>(coffeeShopList.size());
        for (CoffeeShop coffeeShop : coffeeShopList) {
            coffeeShopDetails.add(new CoffeeShopDetail(coffeeShop.getId(), coffeeShop.getName()));
        }

        CoffeeShopListResponse coffeeShopListResponse = new CoffeeShopListResponse(SuccessAppMessage.OK_Coffee_Shop_Details);
        coffeeShopListResponse.setCoffeeShopDetails(coffeeShopDetails);
        return coffeeShopListResponse;
    }

}
