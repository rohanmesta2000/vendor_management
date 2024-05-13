package com.xworkz.vendorManagement.entity;

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
@Table(name = "admin_portal")
@NamedQuery(name = "findAllEntity", query = "Select et from AdminEntity et")
@NamedQuery(name = "findByEmailAndPassword", query = "Select et from AdminEntity et where et.email=:email and et.password=:password")
@NamedQuery(name = "findByEmail", query = "Select et from AdminEntity et where et.email=:email")

public class AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private int id;
	@Column(name = "a_email")
	private String email;
	@Column(name = "a_password")
	private String password;
}
