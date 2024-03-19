package com.xworkz.vManagement.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vManagement.service.VendorService;

@RestController
@RequestMapping("/")
@EnableWebMvc
public class AjaxController {
	
	@Autowired
	private VendorService vendorService;
	
	public AjaxController() {
		System.out.println("In Ajax Controller");
		
	}
	
	@GetMapping(value="/uniqueEmail/{email}")
	public String onEmail(@PathVariable String email) {
		System.out.println("running ajax" +email);
		String refEmail=vendorService.findAllByAjaxEmail(email);
		System.out.println("Email already exist" +refEmail);
		return refEmail;
		
	}
	
	
	@GetMapping(value="/uniqueGSTNo/{gstNo}")
	public String onGSTNo(@PathVariable String gstNo) {
		System.out.println("runing gstNo ajax");
		String refGSTNo=vendorService.findAllByAjaxGSTNo(gstNo);
		System.out.println("GSTNo already exist" +refGSTNo);
		return refGSTNo;
		
	}
	
	
	@GetMapping(value="/uniqueContactNo/{contactNo}")
	public String onContactNo(@PathVariable Long contactNo) {
		System.out.println("runing contactNo ajax");
		String refContactNo=vendorService.findAllByAjaxContactNo(contactNo);
		System.out.println("contactNo already exist" +refContactNo);
		return refContactNo;
		
	}
	
	
	@GetMapping(value="/uniqueWebsite/{website}")
	public String onWebsite(@PathVariable String website) {
		System.out.println("runing website ajax");
		String refWebsite=vendorService.findAllByAjaxWebsite(website);
		System.out.println("website already exist" +refWebsite);
		return refWebsite;
		
	}

}
