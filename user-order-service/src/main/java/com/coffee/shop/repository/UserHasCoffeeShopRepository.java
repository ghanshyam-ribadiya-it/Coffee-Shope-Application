package com.coffee.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.shop.domain.UserHasCoffeeShop;

@Repository
public interface UserHasCoffeeShopRepository extends JpaRepository<UserHasCoffeeShop, Long> {

}
