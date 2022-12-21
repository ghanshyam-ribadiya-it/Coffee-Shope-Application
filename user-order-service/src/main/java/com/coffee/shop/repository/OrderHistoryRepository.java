package com.coffee.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coffee.shop.domain.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, String> {

	@Query("select orderHistory from OrderHistory orderHistory where userHasCoffeeShop.id = :id and orderStatus = 'REQUESTED'")
	List<OrderHistory> findPendingOrdersByCoffeeShopId(@Param(value = "id") Long id);
}
