package com.coffee.shop.model;

import java.util.List;

import com.coffee.shop.constants.AppMessage;

public class MenuListResponse extends AppResponse {
	private List<MenuDetail> menuDetails;

	public MenuListResponse() {
		super();
	}

	public MenuListResponse(AppMessage appMessage) {
		super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
	}

	public List<MenuDetail> getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(List<MenuDetail> menuDetails) {
		this.menuDetails = menuDetails;
	}
}
