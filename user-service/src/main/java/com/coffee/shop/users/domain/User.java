package com.coffee.shop.users.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "user_type_id", nullable = false)
	private UserType userType;
	
	public String getFullName() {
		return this.firstName+ " " + this.lastName;
	}
}
