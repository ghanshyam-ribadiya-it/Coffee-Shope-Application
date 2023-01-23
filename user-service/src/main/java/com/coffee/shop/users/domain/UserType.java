package com.coffee.shop.users.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_type")
@Getter
@Setter
public class UserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
}