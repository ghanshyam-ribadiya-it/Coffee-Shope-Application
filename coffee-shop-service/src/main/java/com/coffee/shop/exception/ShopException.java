package com.coffee.shop.exception;

import com.coffee.shop.constants.AppMessage;

public class ShopException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private AppMessage appMessage;
	
	public ShopException(AppMessage appMessage) {
		super();
		this.appMessage = appMessage;
	}

	public AppMessage getAppMessage() {
		return appMessage;
	}

	public void setAppMessage(AppMessage appMessage) {
		this.appMessage = appMessage;
	}
}
