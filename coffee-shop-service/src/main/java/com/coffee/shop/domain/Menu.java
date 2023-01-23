package com.coffee.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
	private Long id;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "coffee_shop_id", nullable = false)
	private CoffeeShop coffeeShop;
}