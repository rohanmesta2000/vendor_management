package com.xworkz.vManagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xworkz.vManagement.dto.VendorEntity;
import com.xworkz.vManagement.repository.VendorRepo;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepo repo;

	public VendorServiceImpl() {
		System.out.println("service created successfully");
	}

	@Override
	public boolean validateAndSave(VendorEntity entity) {

		System.out.println("invoking validateAndSave in Vendsrserviveimpl");
		entity.setCreatedBy(entity.getName());
		entity.setCreatedDate(LocalDate.now());
		System.out.println("entity passed " + entity);
		this.repo.save(entity);
		return true;
	}

	@Override
	public String isExistByGstContactEmailWebsite(String gstNo, Long contactNo, String email, String website) {
		VendorEntity ref = repo.isExist(gstNo, contactNo, email, website);
		if (ref != null) {
			if (ref.getGstNo().equals(gstNo)) {
				return "gstNo already exist";
			} else if (ref.getEmail().equals(email)) {
				return "email already exist";
			}

			else if (ref.getContactNo().equals(contactNo)) {
				return "Number already exist";
			}

			else if (ref.getWebsite().equals(website)) {
				return "website alredy exist";
			}

			System.out.println("Details not found.... save the details");

		}

		return null;
	}

	@Override
	public boolean sendemail(String email) {

		String portNumber = "587";
		String hostName = "smtp.office365.com";
		String fromEmail = "rohanmesta20@outlook.com";
		String password = "Rohanme1@";
		String to = email;

		Properties prop = new Properties();

		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject("Registration Form");
			message.setText("Thanks for Registering");

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);

			return true;

		}

		catch (MessagingException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public String findAllByAjaxEmail(String email) {
		List<VendorEntity> listEmail = repo.findAllByAjax();
		if (listEmail != null) {
			for (VendorEntity entity : listEmail) {
				if (entity.getEmail().equals(email)) {
					return "Email already exist";
				} else {
					System.out.println("Email present");
				}

			}
		}

		return null;
	}

	@Override
	public String findAllByAjaxGSTNo(String gstNo) {
		List<VendorEntity> listGSTN = repo.findAllByAjax();
		if (listGSTN != null) {
			for (VendorEntity entity : listGSTN) {
				if (entity.getGstNo().equals(gstNo)) {
					return " GSTNo already exist";
				} else {
					System.out.println("GSTNo not present");
				}
			}
		}
		return null;
	}

	@Override
	public String findAllByAjaxContactNo(Long contactNo) {
		List<VendorEntity> listContactNo = repo.findAllByAjax();
		if (listContactNo != null) {
			for (VendorEntity entity : listContactNo) {
				if (entity.getContactNo().equals(contactNo)) {
					return " ContactNo already exist";
				} else {
					System.out.println("ContactNum not present");
				}
			}
		}
		return null;
	}

	@Override
	public String findAllByAjaxWebsite(String website) {
		List<VendorEntity> listWebsite = repo.findAllByAjax();
		System.err.println(listWebsite);
		if (listWebsite != null) {
			for (VendorEntity entity : listWebsite) {
				if (entity.getWebsite().equals(website)) {
					return " Webiste already exist";
				} else {
					System.out.println("Website not present");
				}
			}
		}
		return null;
	}

	@Override
	public String isExist(String email, String otp) {
		 VendorEntity ref=repo.isExistByEmailOtp(email, otp);
		 if(ref!=null) {
			 if(ref.getEmail().equals(email)) {
				 return "Email already exist";
			 }else if (ref.getOtp().equals(otp)) {
				return "OTP already exist";
			}
			 System.out.println("Details not found.............");
		 }
		return null;
	}

	@Override
	public VendorEntity findByEmail(String email) {
     VendorEntity ref=repo.findByEmail(email);
     if(ref!=null) {
    	 System.out.println("entity found");
    	 return ref;
     }
		return null;
	}

}
