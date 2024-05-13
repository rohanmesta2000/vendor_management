package com.xworkz.vendorManagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vendorManagement.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@EnableWebMvc
public class VendorAjaxController {
	@Autowired
	private VendorService service;

	public VendorAjaxController() {
		log.info("Invoking VendorAjaxController");
	}
	
	@GetMapping(value="/gstNoAjax/{gstNo}")
	public String gstNoAjax(@PathVariable String gstNo) {
		String byGstNo=this.service.isExistByGstNoAjax(gstNo);
		log.info("Running ajax controller for gstNo:" +gstNo);
		return byGstNo;
	}
	@GetMapping(value = "/emailAjax/{email:.+}")
	public String emailAjax(@PathVariable String email) {
		String byEmail = this.service.isExistByEmailAjax(email);
		log.info("Running ajax controller for email: " + email);
		return byEmail;
	}
	
	@GetMapping(value = "/contactNoAjax/{contactNo}")
	public String contactNoAjax(@PathVariable Long contactNo) {
		String byContactNo = this.service.isExistByContactNoAjax(contactNo);
		log.info("Running ajax controller for Contact number : " + contactNo);
		return byContactNo;
	}
	
	@GetMapping(value="/alternativeNoAjax/{alternativeNo}")
	public String alternativeNoAjax(@PathVariable Long alternativeNo) {
		String byAlternativeNo=this.service.isExistByAlternativeNoAjax(alternativeNo);
		log.info("Running ajax controller for alternativeNo:" +alternativeNo);
		return byAlternativeNo;
	}
	
	
	@GetMapping(value = "/websiteAjax/{website:.+}")
	public String websiteAjax(@PathVariable String website) {
		String byWebsite = this.service.isExistByWebsiteAjax(website);
		log.info("Running ajax controller for website: " + website);
		return byWebsite;
	}
}
