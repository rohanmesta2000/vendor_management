package com.xworkz.vendorManagement.repository;

import com.xworkz.vendorManagement.entity.AdminEntity;

public interface AdminRepository {
	boolean findEmailAndPassword(String email,String password);

	AdminEntity findByEmail(String email);
}
