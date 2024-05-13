package com.xworkz.vendorManagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.xworkz.vendorManagement.entity.VendorEntity;

public interface VendorRepository {
	public boolean save(VendorEntity entity);

	List<VendorEntity> findAll();

	public VendorEntity isExistGstNoContactNoEmailWebsite(String gstNo, Long contactNo, String email, String website);

	boolean findByEmail(String email);

	 boolean saveLoginOtpByemaild(String email,String otp,LocalDateTime generateOtpTime);
	   
	 Object[] getloginOTPAndgenratedTime(String email);
	 
	 String imagePathByEmail(String email);
	 
	 VendorEntity findAllVendorEntityByEmail(String email);
	 
	 boolean updateVendorDetailsById( VendorEntity entity,int id);
	 
	int getVendorIdByEmail(String email);
}
