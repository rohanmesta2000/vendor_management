package com.xworkz.vendorManagement.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendorManagement.entity.EmailValidationEntity;
import com.xworkz.vendorManagement.repository.EmailRepository;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRepository repo;

	@Autowired
	private MailSending mailSending;

	@Override
	public boolean saveOtpByEmail(String email) {
		if (email != null) {
			Random random = new Random();
			int generatedOtp = random.nextInt(900000) + 100000;
			String otp = String.valueOf(generatedOtp);
			EmailValidationEntity entity = new EmailValidationEntity();
			entity.setEmail(email);
			entity.setOtp(otp);
			entity.setCreatedAt(LocalDateTime.now());
			boolean save = repo.save(entity);
			if (save) {
				boolean sent = mailSending.emailVerficationOtp(email, otp);
				if (sent) {
					return true;
				}

			}
		}
		return false;
	}

	@Override
	public boolean validateEmailVerificationOTP(String email, String otp) {
		if (email != null & otp != null) {
			String latestotp = repo.findLatestOtpByEmail(email);
			System.err.println("latestotp======================" + latestotp);
			if (latestotp != null) {
				if (latestotp.equals(otp)) {
					repo.deleteEmailVerificationDataByEmail(email);
					return true;
				}
			}

		}

		return false;
	}

}
