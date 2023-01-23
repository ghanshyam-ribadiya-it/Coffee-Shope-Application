package com.coffee.shop.repository;

import com.coffee.shop.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select menu from Menu menu where coffeeShop.id = :id")
    public List<Menu> findAllMenuByCoffeeShopId(@Param(value = "id") Long id);

    @Query("select menu from Menu menu where menu.id = :menuId and coffeeShop.id = :coffeeShopId")
    public Menu findMenuByIds(@Param(value = "menuId") Long menuId, @Param(value = "coffeeShopId") Long coffeeShopId);
}