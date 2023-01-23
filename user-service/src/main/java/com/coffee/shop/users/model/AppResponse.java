package com.coffee.shop.users.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class AppResponse {
	
	private int resultCode;
	private String message;
	private List<String> errors;

	protected AppResponse() {

	}

	protected AppResponse(int resultCode, String message, List<String> errors) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.errors = errors;
	}
}