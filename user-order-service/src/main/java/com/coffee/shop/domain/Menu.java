package com.coffee.shop.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
	private UserHasCoffeeShop userHasCoffeeShop;
}
