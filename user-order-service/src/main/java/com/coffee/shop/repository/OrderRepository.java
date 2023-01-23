package com.coffee.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coffee.shop.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

	@Query("select orders from Orders orders where coffeeShopId = :coffeeShopId and orderStatus = 'REQUESTED'")
	List<Orders> findPendingOrdersByCoffeeShopId(@Param(value = "coffeeShopId") Long coffeeShopId);
}
