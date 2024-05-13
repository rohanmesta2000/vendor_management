package com.xworkz.vManagement.repository;

import java.util.List;

import com.xworkz.vManagement.dto.VendorEntity;

public interface VendorRepo {
	public boolean save(VendorEntity entity);

	public VendorEntity isExist(String gstNo,Long contactNo,String email, String website);
	
	public List<VendorEntity> findAllByAjax();
	
	public  VendorEntity isExistByEmailOtp(String email,String otp);
	
	public void updatedOtpByEmail(String email,String otp);
	
	VendorEntity findByEmail(String email);
	
	List<VendorEntity> findByName(String name);
	
	
}
