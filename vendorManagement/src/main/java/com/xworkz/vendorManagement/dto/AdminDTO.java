package com.xworkz.vendorManagement.dto;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class AdminDTO {
	@Id
	private int id;
	@NotNull
	@Email
	private String email;
	@Size(min = 8, message = "Password must be minimum 8 characters")
	private String password;
}

