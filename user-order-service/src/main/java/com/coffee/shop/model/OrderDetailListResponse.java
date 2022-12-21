package com.coffee.shop.model;

import java.util.List;

import com.coffee.shop.constants.AppMessage;

public class OrderDetailListResponse extends AppResponse {
	private List<OrderDetail> orderDetails;
	
	public OrderDetailListResponse() {
		super();
	}

	public OrderDetailListResponse(AppMessage appMessage) {
		super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
