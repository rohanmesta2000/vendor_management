package com.xworkz.vendorManagement.entity;

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
@Table(name = "add_product")
@NamedQuery(name = "readAll", query = "Select et from ProductEntity et")
@NamedQuery(name="getAllProductDetailesByVendotId",query="Select entity from ProductEntity entity WHERE entity.vendor.id=:vendorId")
@NamedQuery(name="getAllProductsById",query="Select entity from ProductEntity entity WHERE entity.id=:id")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="category")
	private String category;
	@Column(name="productName")
	private String productName;
	@Column(name="product_price")
	private double productPrice;
	@Column(name="delivery_charge")
	private double deliveryCharge;
	@Column(name="description")
	private String description;
	@Column(name="available")
	private String available;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id")
	private VendorEntity vendor;
	
}


