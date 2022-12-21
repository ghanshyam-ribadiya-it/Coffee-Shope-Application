package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;

public class OrderDetailResponse extends AppResponse {
	private OrderDetail orderDetail;
	
	public OrderDetailResponse() {
		super();
	}

	public OrderDetailResponse(AppMessage appMessage) {
		super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
}
