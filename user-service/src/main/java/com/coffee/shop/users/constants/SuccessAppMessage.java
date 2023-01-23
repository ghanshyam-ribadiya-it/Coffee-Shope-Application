package com.coffee.shop.users.constants;

import java.util.List;

public final class SuccessAppMessage implements AppMessage {

	public static final AppMessage OK = new SuccessAppMessage(200, "OK");
	public static final AppMessage OK_User_Saved = new SuccessAppMessage(200, "User details are saved successfully");
	public static final AppMessage OK_User_Details = new SuccessAppMessage(200, "User details");

	final int resultCode;
	final String message;
	final List<String> errors;

	public SuccessAppMessage(int resultCode, String message) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.errors = null;
	}

	public SuccessAppMessage(int resultCode, String message, List<String> errors) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.errors = errors;
	}

	@Override
	public int getResultCode() {
		return resultCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public List<String> getErrors() {
		return errors;
	}

}