package com.xworkz.vManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.vManagement.dto.VendorEntity;
import com.xworkz.vManagement.service.VendorService;

@Controller
@RequestMapping("/")
public class RegistrationController {

	@Autowired
	private VendorService vendorService;

	public RegistrationController() {
		System.out.println("Invoking RegistrationController............");
	}

	@PostMapping("/vendorManage")
	public String save(@Valid VendorEntity entity, BindingResult errors, Model model) {

		System.out.println("entity" + entity);
		System.out.println("Is VendorEntity is invalid : " + errors.hasErrors());
		/* model.addAttribute("msg", "saved succesfully...."); */

		if (errors.hasErrors()) {
			List<ObjectError> objectErrors = errors.getAllErrors();
			objectErrors.forEach(objectError -> System.err
					.println(objectError.getObjectName() + ":message" + objectError.getDefaultMessage()));
			model.addAttribute("entity", entity);
			model.addAttribute("errors", objectErrors);
			return "registration";

		} else {

			String uniqueError = vendorService.isExistByGstContactEmailWebsite( entity.getGstNo(),entity.getContactNo(),entity.getEmail(),
					entity.getWebsite());
			if (uniqueError != null) {
				
				model.addAttribute("uniqueError", uniqueError);
				System.out.println("mail already exist..." +uniqueError);
				return "registration";
			} else {
				model.addAttribute("msg", "Vendor information saved successfully");
			}
			this.vendorService.validateAndSave(entity);
			this.vendorService.sendemail(entity.getEmail());
			return "registration";
		}
	}

}
