package com.coffee.shop.repository;

import com.coffee.shop.domain.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeShopRepository extends JpaRepository<CoffeeShop, Long> {
    List<CoffeeShop> findByAreaCode(String areaCode);
}
