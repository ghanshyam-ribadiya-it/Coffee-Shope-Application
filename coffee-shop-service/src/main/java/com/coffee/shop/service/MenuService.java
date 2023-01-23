package com.coffee.shop.service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.constants.SuccessAppMessage;
import com.coffee.shop.domain.Menu;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.model.MenuDetail;
import com.coffee.shop.model.MenuListResponse;
import com.coffee.shop.model.MenuResponse;
import com.coffee.shop.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public MenuResponse getMenuById(Long menuId) {
        log.info("Find Menu by menuId : {}", menuId);

        Optional<Menu> menu = menuRepository.findById(menuId);
        if (menu.isPresent()) {
            MenuResponse menuResponse = new MenuResponse(SuccessAppMessage.OK_Menu_Details);
            menuResponse.setMenuDetail(mapMenuToMenuDetail(menu.get()));
            return menuResponse;
        }

        throw new ShopException(ErrorAppMessage.MENU_NOT_FOUND);
    }

    private MenuDetail mapMenuToMenuDetail(Menu menu) {
        return modelMapper.map(menu, MenuDetail.class);
    }

    @Transactional
    public MenuListResponse findAllMenuByCoffeeShopId(Long coffeeShopId) {
        log.info("Find All Menu By CoffeeShopId : {}", coffeeShopId);

        List<Menu> menuList = menuRepository.findAllMenuByCoffeeShopId(coffeeShopId);

        List<MenuDetail> menuDetails = new ArrayList<>(menuList.size());
        for (Menu menu : menuList) {
            menuDetails.add(mapMenuToMenuDetail(menu));
        }

        MenuListResponse menuListResponse = new MenuListResponse(SuccessAppMessage.OK_Menu_Details);
        menuListResponse.setMenuDetails(menuDetails);
        return menuListResponse;
    }

    @Transactional
    public MenuResponse getMenuByIds(Long coffeeShopId, Long menuId) {
        log.info("Get Menu By coffeeShopId : {}, menuId : {}", coffeeShopId, menuId);

        Menu menu = menuRepository.findMenuByIds(menuId, coffeeShopId);
        if (menu == null) {
            throw new ShopException(ErrorAppMessage.MENU_NOT_FOUND);
        }

        MenuResponse menuResponse = new MenuResponse(SuccessAppMessage.OK_Menu_Details);
        menuResponse.setMenuDetail(mapMenuToMenuDetail(menu));
        return menuResponse;
    }
}
