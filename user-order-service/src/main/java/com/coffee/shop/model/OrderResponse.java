package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;

public class OrderResponse extends AppResponse {
	private String orderId;
	
	public OrderResponse() {
		super();
	}

	public OrderResponse(AppMessage appMessage) {
		super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
