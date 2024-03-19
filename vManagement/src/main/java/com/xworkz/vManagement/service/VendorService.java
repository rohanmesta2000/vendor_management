package com.xworkz.vManagement.service;

import com.xworkz.vManagement.dto.VendorEntity;

public interface VendorService {
	boolean validateAndSave(VendorEntity entity);

	String isExistByGstContactEmailWebsite(String gstNo,Long contactNo,String email, String website);

	public boolean sendemail(String email);
	
	
	public String findAllByAjaxEmail(String email);
	
	public String findAllByAjaxGSTNo(String gstNo);
	
	public String findAllByAjaxContactNo(Long contactNo);
	
    public String findAllByAjaxWebsite(String website);
    
    public String isExist(String email,String otp);
    
    VendorEntity findByEmail(String email);
}
