package com.xworkz.vManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.vManagement.dto.VendorEntity;
import com.xworkz.vManagement.service.VendorService;

@Component
@RequestMapping("/")
public class ViewController {
	public ViewController() {
System.out.println("Invoking View Controller");
	}
	
	@Autowired
	private VendorService service;
	
	@PostMapping("/views")
	public String showing(@Valid VendorEntity entity,@PathVariable String email,Model model) {
		
		System.out.println("Invoking Showing()");
		
		String entity1=service.findAllByAjaxEmail(email);
		model.addAttribute("entites",entity1);
		return "view";
	}
	

}
