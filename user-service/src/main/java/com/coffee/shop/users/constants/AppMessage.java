package com.coffee.shop.users.constants;

import java.util.List;

public interface AppMessage {
	public int getResultCode();

	public String getMessage();

	public List<String> getErrors();
}
