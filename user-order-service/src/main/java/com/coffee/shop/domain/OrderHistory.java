package com.coffee.shop.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import com.coffee.shop.constants.OrderStatus;
import com.coffee.shop.constants.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_history")
@Getter
@Setter
public class OrderHistory {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "order_id", nullable = false)
	private String orderId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Users customer;

	@ManyToOne
	@JoinColumn(name = "coffee_shop_id", nullable = false)
	private UserHasCoffeeShop userHasCoffeeShop;

	@ManyToOne
	@JoinColumn(name = "menu_id", nullable = false)
	private Menu menu;
	
	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "total_payment", nullable = false)
	private BigDecimal totalPayment;

	@Column(name = "order_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name = "payment_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Generated(GenerationTime.INSERT)
	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdAt;
}
