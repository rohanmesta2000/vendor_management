package com.xworkz.vendorManagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="order_table")
@NamedQuery(name="viewAllOrdersByVendorId",query="Select entity from OrderEntity enity where entity.vendor.id=:vendorId")
@NamedQuery(name="getAllOrdersById",query="Select entity from OrderEntity entity WHERE entity.id=:id")

public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	
	@Column(name="category")
	private String category;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_price")
	private double productPrice;
	
	@Column(name="delivery_charge")
	private double deliveryCharge;
	@Column(name="description")
	 private String description;
	@Column(name="available")
	private String available;
	@Column(name="order_quantity")
	 private int orderQuantity;
	@Column(name="order_date")
	private LocalDateTime orderDate;
	@Column(name="delivery_date")
	private String deliveryDate;
	@Column(name="delivery_address")
	private String deliveryAddress;
	@Column(name="message")
	private String message;
	@Column(name="order_status")
	 private String orderStatus;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductEntity product;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="vendor_id")
	private VendorEntity vendor;
	
	 @Column(name="order_amount")
	 private double orderAmount;
	
	@Column(name="amount_paid")
	private double amountPaid;
	
	@Column(name="payment_status")
	private String paymentStatus;
	
	@Column(name="total_amount_to_pay")
	private double totalAmountToPay;
	
	
	@Column(name="balance_amount")
	private double balanceAmount;

	
}

