package com.coffee.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "coffee_shop")
@Getter
@Setter
public class CoffeeShop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address_line_1", nullable = false)
	private String addressLine1;

	@Column(name = "address_line_2", nullable = false)
	private String addressLine2;

	@Column(name = "area_code", nullable = false)
	private String areaCode;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "user_id", nullable = false)
	private Long userId;
}