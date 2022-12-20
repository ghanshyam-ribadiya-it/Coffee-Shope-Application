package com.coffee.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	private Long requestedUserId;
	private Long coffeeShopeId;
	private Long menuId;
	private Integer quantity;
}
