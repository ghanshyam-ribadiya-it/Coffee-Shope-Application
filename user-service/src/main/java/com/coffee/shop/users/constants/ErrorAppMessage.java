package com.coffee.shop.users.constants;

import java.util.List;

public final class ErrorAppMessage implements AppMessage {

	public static final AppMessage SERVER_ERROR = new ErrorAppMessage(500, "Internal Server Error");
	public static final AppMessage USER_NOT_FOUND = new ErrorAppMessage(411, "User is not found for the requestedUserId");

	final int resultCode;
	final String message;
	final List<String> errors;

	public ErrorAppMessage(int resultCode, String message) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.errors = null;
	}

	public ErrorAppMessage(int resultCode, String message, List<String> errors) {
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