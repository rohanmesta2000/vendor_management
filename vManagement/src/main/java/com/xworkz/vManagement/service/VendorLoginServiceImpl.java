package com.xworkz.vManagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vManagement.dto.VendorEntity;
import com.xworkz.vManagement.repository.VendorRepo;
import com.xworkz.vManagement.util.EmailSender;
import com.xworkz.vManagement.util.OtpGenerator;

@Service
public class VendorLoginServiceImpl implements VendorLoginService {

	@Autowired
	private VendorRepo repo;

	@Autowired
	private EmailSender emailSender;

	public VendorLoginServiceImpl() {
		System.out.println("invoking in VendorLoginServiceImpl");

	}

	@Override
	public String emailLoginAjax(String email) {
		System.out.println("invoking emailLoginAjax in VendorLoginServiceImpl");
		List<VendorEntity> entity = this.repo.findAllByAjax();
		for (VendorEntity entities : entity) {
			if (entities.getEmail().equals(email)) {
				System.out.println("Email is Verified");
				return "";
			}
		}

		return "Please register account";
	}

	@Override
	public String loginOtpEmailMsg(String email) {
		System.out.println("invoking loginOtpEmailMsg in VendorLoginServiceImpl");
		String otp = OtpGenerator.generatorOTP();
		String text = "Your OTP for Login :" + otp;
		String subject = "One Time Password";
		String to = email;
		String from = "rohanmesta20@outlook.com";

		boolean emailOTP = this.emailSender.emailSender(to, from, subject, text);
		System.out.println("mail sent " + emailOTP);
		this.repo.updatedOtpByEmail(email, otp);
		if (emailOTP) {
			return "OTP Sent Successfully";
		}
		return null;
	}

	@Override
	public String loginOtpAjax(String otp) {
		System.out.println("invoking loginOtpAjax in VendorLoginServiceImpl");
		List<VendorEntity> entity = this.repo.findAllByAjax();
		for (VendorEntity entities : entity) {
			System.out.println("checking for" + otp + "--" + entities.getOtp());

			if (entities.getOtp() != null) {

				if (entities.getOtpGeneratedTime().plusMinutes(10l).compareTo(LocalDateTime.now()) == 10) {
					System.out.println("otp generated time" +entities.getOtpGeneratedTime());
					System.out.println("time check" + LocalDateTime.now());
					
					return "otp expired";
				}	

				if (entities.getOtp().equals(otp)) {
					return "";
				}
				System.out.println("checking for" + otp);
			}
		}
		return "OTP Not Matched";
	}

}
