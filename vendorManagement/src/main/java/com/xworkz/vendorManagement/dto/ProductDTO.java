package com.xworkz.vendorManagement.dto;

import javax.persistence.Id;

import com.xworkz.vendorManagement.entity.VendorEntity;

import lombok.Data;
@Data
public class ProductDTO {

	@Id
	private int id;
	private String category;
	private String productName;
	private double productPrice;
	private double deliveryCharge;
	private String description;
	private String available;
	private String email;
	private VendorEntity vendor;
}
