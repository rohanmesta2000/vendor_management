package com.xworkz.vManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vManagement.dto.VendorEntity;
import com.xworkz.vManagement.repository.AdminRepo;
import com.xworkz.vManagement.repository.VendorRepo;


@Service
public class AdminLoginServiceImpl implements AdminLoginService {

	
	@Autowired
	public VendorRepo vendorRepsoitory;
	
	@Autowired
	public AdminRepo repos;
	
	
	@Override
	public boolean findEmailAndPassword(String email, String password) {
       
		boolean en=repos.findEmailAndPassword(email, password);
		
		return en;
	}

	@Override
	public List<VendorEntity> getAllUsers() {
 
		
		return vendorRepsoitory.findAllByAjax();
	}

	@Override
	public List<VendorEntity> findByName(String name) {
		// TODO Auto-generated method stub
		return vendorRepsoitory.findByName(name);
		
	}

}
