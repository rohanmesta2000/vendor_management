package com.xworkz.vManagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "vmanagementRegister")

@NamedQuery(name = "isExistContactGstEmailWebsite", query = "SELECT ent from  VendorEntity as ent where ent.gstNo=:gn or ent.contactNo=:cn or  ent.email=:em or ent.website=:we")
@NamedQuery(name = "FindAllByAjax", query = "select ent from VendorEntity ent")
@NamedQuery(name = "updatedOtpByEmail", query = "select ent from VendorEntity ent where ent.email=:email")
@NamedQuery(name = "isExistByEmailOtp", query = "select ent from VendorEntity ent where ent.email=:email or ent.otp=:otp")
public class VendorEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotNull
	@Size(min = 3, max = 30, message = "name must be between 3 to 30 characters")
	private String name;

	@Column(name = "location")
	@NotNull
	@Size(min = 5, max = 30, message = "loaction must be between 3 to 30 characters")
	private String location;

	@Column(name = "gstNo")
	@NotNull
	@Size(min = 4, max = 30, message = " gst must be between 4 to 30 characters")
	private String gstNo;
    
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "companyStartDate")
	private LocalDate companyStartDate;

	@Column(name = "ownerName")
	@NotNull
	@Size(min = 3, max = 30, message = "name must be between 3 to 30 characters")
	private String ownerName;

	@Column(name = "serviceType")
	@NotNull
	@Size(min = 4, max = 30, message = "name must be between 3 to 30 characters")
	private String serviceType;

	@Column(name = "contactNo")
	@NotNull
	private Long contactNo;

	@Column(name = "alternativeNo")
	@NotNull
	private Long alternativeNo;

	@Column(name = "email")
	@NotNull
	@Email
	private String email;

	@Column(name = "website")
	@NotNull
	private String website;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "createdDate")
	private LocalDate createdDate;

	@Column(name = "updateBy")
	private String updateBy;

	@Column(name = "updateDate")
	private LocalDate updateDate;

	@Column(name="otp")
	private String otp;
	
	
	@Column(name="otpGeneratedTime")
	private LocalDateTime otpGeneratedTime;
	
	
}
