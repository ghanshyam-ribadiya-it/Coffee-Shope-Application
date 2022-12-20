package com.coffee.shop.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.shop.constants.OrderStatus;
import com.coffee.shop.constants.PaymentStatus;
import com.coffee.shop.constants.SuccessAppMessage;
import com.coffee.shop.domain.Menu;
import com.coffee.shop.domain.OrderHistory;
import com.coffee.shop.domain.UserHasCoffeeShop;
import com.coffee.shop.domain.Users;
import com.coffee.shop.model.OrderRequest;
import com.coffee.shop.model.OrderResponse;
import com.coffee.shop.repository.OrderHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderHistoryService {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CoffeeShopService coffeeShopService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Transactional
	public OrderResponse saveRequestedOrder(OrderRequest orderRequest) {
		log.info("Request is received for the new Order : {} ", orderRequest);

		Users user = usersService.getUserById(orderRequest.getRequestedUserId());
		UserHasCoffeeShop coffeeShop = coffeeShopService.getCoffeeShopById(orderRequest.getCoffeeShopeId());
		Menu menu = menuService.getMenuById(orderRequest.getMenuId());
		
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setUser(user);
		orderHistory.setUserHasCoffeeShop(coffeeShop);
		orderHistory.setMenu(menu);
		orderHistory.setQuantity(orderRequest.getQuantity());
		orderHistory.setTotalPayment(calculateCost(orderRequest.getQuantity(), menu.getPrice()));		
		orderHistory.setOrderStatus(OrderStatus.REQUESTED);
		orderHistory.setPaymentStatus(PaymentStatus.PENDING);
		
		orderHistoryRepository.save(orderHistory);
		
		OrderResponse orderResponse = new OrderResponse(SuccessAppMessage.OK_ORDER_PLACED);
		orderResponse.setOrderId(orderHistory.getOrderId());
		return orderResponse;
	}
	
	public BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
		BigDecimal itemCost = new BigDecimal(BigInteger.ZERO, 2);
		BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);
		
		itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
		return totalCost.add(itemCost);
	}
}
