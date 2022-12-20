package com.coffee.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffee.shop.model.OrderRequest;
import com.coffee.shop.model.OrderResponse;
import com.coffee.shop.service.OrderHistoryService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@PostMapping("/create")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse orderResponse = orderHistoryService.saveRequestedOrder(orderRequest);
		return ResponseEntity.ok(orderResponse);
	}

}