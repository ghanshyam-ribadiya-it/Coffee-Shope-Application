package com.coffee.shop.model;

import java.math.BigDecimal;

import com.coffee.shop.constants.OrderStatus;
import com.coffee.shop.constants.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
	private String orderId;
	private CustomerDetail customerDetail;
	private CoffeeShopDetail coffeeShopDetail;
	private MenuDetail menuDetail;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal totalPayment;
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;
}
