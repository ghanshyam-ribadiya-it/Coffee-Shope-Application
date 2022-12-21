package com.coffee.shop.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.constants.OrderStatus;
import com.coffee.shop.constants.PaymentStatus;
import com.coffee.shop.constants.SuccessAppMessage;
import com.coffee.shop.domain.Menu;
import com.coffee.shop.domain.OrderHistory;
import com.coffee.shop.domain.UserHasCoffeeShop;
import com.coffee.shop.domain.Users;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.model.CoffeeShopDetail;
import com.coffee.shop.model.CustomerDetail;
import com.coffee.shop.model.MenuDetail;
import com.coffee.shop.model.OrderDetail;
import com.coffee.shop.model.OrderDetailListResponse;
import com.coffee.shop.model.OrderDetailResponse;
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

		Users customer = usersService.getUserById(orderRequest.getCustomerId());
		UserHasCoffeeShop coffeeShop = coffeeShopService.getCoffeeShopById(orderRequest.getCoffeeShopeId());
		Menu menu = menuService.getMenuById(orderRequest.getMenuId());
		
		validateMenu(coffeeShop, menu);
		
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setCustomer(customer);
		orderHistory.setUserHasCoffeeShop(coffeeShop);
		orderHistory.setMenu(menu);
		orderHistory.setPrice(menu.getPrice());
		orderHistory.setQuantity(orderRequest.getQuantity());
		orderHistory.setTotalPayment(calculateCost(orderRequest.getQuantity(), menu.getPrice()));		
		orderHistory.setOrderStatus(OrderStatus.REQUESTED);
		orderHistory.setPaymentStatus(PaymentStatus.PENDING);
		
		orderHistoryRepository.save(orderHistory);
		
		OrderResponse orderResponse = new OrderResponse(SuccessAppMessage.OK_ORDER_PLACED);
		orderResponse.setOrderId(orderHistory.getOrderId());
		return orderResponse;
	}
	
	private void validateMenu(UserHasCoffeeShop coffeeShop, Menu menu) {
		if(menu.getUserHasCoffeeShop().getId() != coffeeShop.getId()) {
			throw new ShopException(ErrorAppMessage.INVALID_MENU);
		}
	}

	private BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
		BigDecimal itemCost = new BigDecimal(BigInteger.ZERO, 2);
		BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);
		
		itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
		return totalCost.add(itemCost);
	}
	
	@Transactional
	public OrderDetailResponse getOrderDetails(String orderId) {
		Optional<OrderHistory> orderHistory = orderHistoryRepository.findById(orderId);
		if(orderHistory.isPresent() == false) {
			throw new ShopException(ErrorAppMessage.INVALID_ORDER_ID);
		}
		
		OrderDetailResponse orderDetailResponse = new OrderDetailResponse(SuccessAppMessage.OK_ORDER_DETAILS);
		orderDetailResponse.setOrderDetail(mapDomainToModelObject(orderHistory.get()));
		return orderDetailResponse;
	}
	
	private OrderDetail mapDomainToModelObject(OrderHistory orderHistoryDetail) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(orderHistoryDetail.getOrderId());
		orderDetail.setCustomerDetail(new CustomerDetail(orderHistoryDetail.getCustomer().getId(), orderHistoryDetail.getCustomer().getFullName()));
		orderDetail.setCoffeeShopDetail(new CoffeeShopDetail(orderHistoryDetail.getUserHasCoffeeShop().getId(), orderHistoryDetail.getUserHasCoffeeShop().getName()));
		orderDetail.setMenuDetail(new MenuDetail(orderHistoryDetail.getMenu().getId(), orderHistoryDetail.getMenu().getItemName(), orderHistoryDetail.getMenu().getPrice()));
		orderDetail.setPrice(orderHistoryDetail.getPrice());
		orderDetail.setQuantity(orderHistoryDetail.getQuantity());
		orderDetail.setTotalPayment(orderHistoryDetail.getTotalPayment());
		orderDetail.setOrderStatus(orderHistoryDetail.getOrderStatus());
		orderDetail.setPaymentStatus(orderHistoryDetail.getPaymentStatus());
		return orderDetail;
	}
	
	@Transactional
	public OrderDetailListResponse retriveQueueOrders(Long coffeeShopId) {
		List<OrderHistory> orderHistoryList = orderHistoryRepository.findPendingOrdersByCoffeeShopId(coffeeShopId);
		
		List<OrderDetail> orderDetails = new ArrayList<>(orderHistoryList.size());
		for(OrderHistory orderHistory : orderHistoryList) {
			orderDetails.add(mapDomainToModelObject(orderHistory));
		}
		
		OrderDetailListResponse orderDetailListResponse = new OrderDetailListResponse(SuccessAppMessage.OK_ORDER_DETAILS);
		orderDetailListResponse.setOrderDetails(orderDetails);
		return orderDetailListResponse;
	}
}
