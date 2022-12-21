package com.coffee.shop.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MenuDetail {
	private Long id;
	private String itemName;
	private BigDecimal price;
}
