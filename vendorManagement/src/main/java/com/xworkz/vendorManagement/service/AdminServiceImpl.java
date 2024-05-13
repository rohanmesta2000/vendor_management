package com.xworkz.vendorManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendorManagement.entity.AdminEntity;
import com.xworkz.vendorManagement.repository.AdminRepository;
import com.xworkz.vendorManagement.repository.VendorRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public boolean findEmailAndPassword(String email, String password) {

		AdminEntity en = adminRepository.findByEmail(email);
		if (en != null && en.getEmail() != null) {
			if (en.getEmail().equals(email) && password.equals(en.getPassword())) {
				return true;
			}
		}
		return false;
	}


}

