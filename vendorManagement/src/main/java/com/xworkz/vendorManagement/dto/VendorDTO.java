package com.xworkz.vendorManagement.dto;


import java.sql.Date;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendorDTO {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min = 3, max = 30, message = "OwnerName must be between 3-30 characters")
	private String ownerName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private Long contactNo;
	@NotNull
	private Long alternativeNo;
	@NotNull
	@Size(min = 3, max = 30, message = "VendorName must be between 3-30 characters")
	private String vendorName;
	@NotNull
	@Size(min = 3, max = 30, message = "GstNo must be between 3-30 characters")
	private String gstNo;
	@NotNull
	@Size(min = 3, max = 30, message = "Address must be between 3-30 characters")
	private String address;
	private Date companyStartDate;
	@NotNull
	@Size(min = 3, message = "Website link must be min 300 characters")
	private String website;
	@NotNull
	private long pincode;
	private String otp;
	
	private String imagePath;
	private MultipartFile imageFile;

}
