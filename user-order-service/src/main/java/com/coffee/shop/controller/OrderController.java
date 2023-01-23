package com.coffee.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.shop.model.OrderDetailListResponse;
import com.coffee.shop.model.OrderDetailResponse;
import com.coffee.shop.model.OrderRequest;
import com.coffee.shop.model.OrderResponse;
import com.coffee.shop.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse orderResponse = orderService.saveRequestedOrder(orderRequest);
		return ResponseEntity.ok(orderResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable String id) {
		OrderDetailResponse orderDetailResponse = orderService.getOrderDetails(id);
		return ResponseEntity.ok(orderDetailResponse);
	}
	
	@GetMapping("/queue/coffeeShop/{id}")
	public ResponseEntity<OrderDetailListResponse> getOrderQueue(@PathVariable Long id) {
		OrderDetailListResponse orderDetailListResponse = orderService.retrieveQueueOrders(id);
		return ResponseEntity.ok(orderDetailListResponse);
	}

}