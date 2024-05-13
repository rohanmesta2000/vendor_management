package com.xworkz.vManagement.service;

import java.util.List;

import com.xworkz.vManagement.dto.VendorEntity;

public interface AdminLoginService {

	boolean findEmailAndPassword(String email,String password);
	
	public List<VendorEntity> getAllUsers();
	
	List<VendorEntity> findByName(String name);
}
