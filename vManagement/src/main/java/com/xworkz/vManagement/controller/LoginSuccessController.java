package com.xworkz.vManagement.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.vManagement.dto.VendorEntity;

@Component
@RequestMapping("/")
public class LoginSuccessController {
   public LoginSuccessController() {
	   System.out.println("Created LoginSuceessController");
}

   
    @PostMapping("/jail")
    public String view(@Valid VendorEntity entity,Model model) {
    	return "loginSuccess";
    }
}
