package com.coffee.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coffee.shop.domain.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

	@Query("select menu from Menu menu where userHasCoffeeShop.id = :id")
	public List<Menu> findAllMenuByCoffeeShopId(@Param(value = "id") Long id);

}