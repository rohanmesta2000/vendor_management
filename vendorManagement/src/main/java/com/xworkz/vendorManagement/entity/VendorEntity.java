package com.xworkz.vendorManagement.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vendor_registration")
@NamedQuery(name = "findAll", query = "Select et from VendorEntity et")
@NamedQuery(name = "isExistGstNoContactNoEmailWebsite", query = "Select ent from VendorEntity as ent where ent.gstNo=:gn   or ent.contactNo=:cn or ent.email=:vm or ent.website=:web")
@NamedQuery(name = "countByEmail", query = "SELECT COUNT(entity) FROM VendorEntity entity WHERE entity.email = :email")
@NamedQuery(name = "saveLoginOtpByemaild", query = "UPDATE VendorEntity entity set entity.otp=:otp,entity.generateOtpTime=:generateOtpTime where entity.email=:email")
@NamedQuery(name = "getloginOTPAndgenratedTime", query = "SELECT entity.otp ,entity.generateOtpTime FROM VendorEntity entity  WHERE entity.email = :email ORDER BY entity.generateOtpTime DESC")
@NamedQuery(name = "findImagePathByEmail", query = "SELECT entity.imagePath FROM VendorEntity entity  WHERE entity.email = :email ")
@NamedQuery(name = "updateVendorDetailsByEmail", query = "UPDATE VendorEntity v " + "SET v.ownerName = :ownerName, "
		+ "v.contactNo = :contactNo, " + "v.alternativeNo = :alternativeNo, " + "v.vendorName = :vendorName, "
		+ "v.gstNo = :gstNo, " + "v.address = :address, " + "v.companyStartDate = :companyStartDate, "
		+ "v.website = :website, " + "v.pincode = :pincode " + "WHERE v.email = :email")
@NamedQuery(name = "findAllVendorEntityByEmail", query = "select ent from VendorEntity ent where ent.email=:email")
@NamedQuery(name = "getVendorIdByEmail", query = "SELECT entity from VendorEntity entity where entity.email=:email")

public class VendorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_id")
	private int id;
	@Column(name = "owner_name")
	private String ownerName;
	@Column(name = "email")
	private String email;
	@Column(name = "contact_no")
	private Long contactNo;
	@Column(name = "alternative_no")
	private Long alternativeNo;

	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "gst_no")
	private String gstNo;
	@Column(name = "company_start_date")
	private Date companyStartDate;
	@Column(name = "website")
	private String website;
	@Column(name = "address")
	private String address;
	@Column(name = "pincode")
	private long pincode;
	private String otp;
	private LocalDateTime generateOtpTime;
	private String imagePath;

}