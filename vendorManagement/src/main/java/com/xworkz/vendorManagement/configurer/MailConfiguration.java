package com.xworkz.vendorManagement.configurer;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {
	public JavaMailSenderImpl mailConfig() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("rohanmesta157@gmail.com");
		mailSender.setPassword("acjd kvqy itsd mxvw");
		Properties properties=mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol","smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug","true");
		return mailSender;
	}
}