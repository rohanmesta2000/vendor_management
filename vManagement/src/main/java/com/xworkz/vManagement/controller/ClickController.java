package com.xworkz.vManagement.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class ClickController {

	public ClickController() {
		System.out.println("created clickComponent");
	}

	@PostMapping("/click")
	public String onClick() {
		System.out.println("Invoking onClick");
		return "index";
	}
}
