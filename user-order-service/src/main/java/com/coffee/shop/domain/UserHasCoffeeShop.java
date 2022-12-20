package com.coffee.shop.domain;

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
@Table(name = "user_has_coffee_shop")
@Getter
@Setter
public class UserHasCoffeeShop {
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

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;
}
