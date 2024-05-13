package com.xworkz.vendorManagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vendorManagement.service.VendorService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class SignUpValidationcontroller {
	@Autowired
	private VendorService service;

	@GetMapping(value = "/checkEmailExistence/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmail(@PathVariable String email) {
		log.info("emailId=================" + email);
		boolean emailExistsInDatabase = service.findByEmail(email);
		if (emailExistsInDatabase) {
			log.info("emailExistsInDatabase==="+emailExistsInDatabase);
			return "EmailExists";
		} else {
			return " not exists";
		}
	}

}

