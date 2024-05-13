package com.xworkz.vendorManagement.repository;

import com.xworkz.vendorManagement.entity.EmailValidationEntity;


public interface EmailRepository {
	boolean save(EmailValidationEntity emailValidationEntity);
	String findLatestOtpByEmail(String email);
	boolean deleteEmailVerificationDataByEmail(String email);
}

