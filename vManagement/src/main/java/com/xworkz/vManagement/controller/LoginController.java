package com.xworkz.vManagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation  .RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.vManagement.dto.VendorEntity;
import com.xworkz.vManagement.repository.VendorRepo;
import com.xworkz.vManagement.service.AdminLoginService;
import com.xworkz.vManagement.service.VendorService;


@Component
@RequestMapping("/")
public class LoginController {
	@Autowired
	private VendorService service;
	
	
	 @Autowired
	 private AdminLoginService adminservice;
	 
	 
	@Autowired
	private VendorRepo repo;

	
	public LoginController() {
    System.out.println("created LoginController()" );
	}
	
	@RequestMapping("/signIn")
	public String loginUsingEmailAndOtp(String email,Model model,HttpSession session) {
		System.out.println("loginUsingEmailAndOtp");
		service.findAllByAjaxEmail(email);
		session.setAttribute("loggedInEmail", email);
		return "loginSuccess";
		
	}
	
	@GetMapping("/views")
	public String getViewPage(@RequestParam String email,Model model) {
		System.out.println("Creating the loginUsingEmailAndOtp");
		VendorEntity entity=this.service.findByEmail(email);
		model.addAttribute("entity",entity);
		return "view";
		
	}
}
