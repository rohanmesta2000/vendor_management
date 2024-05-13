package com.xworkz.vManagement.dto;



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

import lombok.Data;


@Data
@Entity
@Table(name="admin_portal")
 @NamedQuery(name = "findAllEntity", query = "select ent from AdminEntity ent")
@NamedQuery(name = "findByEmailAndPassword", query = "Select et from AdminEntity ent where ent.email=:email and ent.password=:password")
public class AdminEntity {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Email
	@Column(name = "a_email")
	private String email;
	
	
	@NotNull
	@Column(name="a_password")
	@Size(min = 8, message = "Password must be minimum 8 characters")
	private String password;

	
	
	
	

}
