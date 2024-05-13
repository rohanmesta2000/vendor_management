
package com.xworkz.vManagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.vManagement.service.AdminLoginService;

@Controller
@RequestMapping("/")

public class AdminController {
	
	public AdminController() {
System.out.println("Invoking AdminLoginController");

	}
	
	@Autowired
	 private AdminLoginService adminLoginService;
	
	@PostMapping("/adminPortal")
	public String findByEmailAndPassword(@RequestParam String email,String password,Model model,HttpSession session) {
		adminLoginService.getAllUsers();
			String userEmail=email;
			session.setAttribute("loggedInEmail", email);
			boolean find=adminLoginService.findEmailAndPassword(email, password);
			if (find) {
				System.out.println("LovginSucess");
				return "adminSuccess";
			}
			 else { 
				 System.out.println("Login failed..."); 
			  }
			return "Invalid email or password...";		
	}
}

/* 
 * @PostMapping("/search")
 *  public String searchByName(@RequestParam String
 * name,Model model) { List<VendorEntity>
 * entities=adminLoginService.findByName(name); model.addAttribute("users",
 * entities); return "adminSuccess";
 * 
 * }
 * 
 * @PostMapping("/viewAll") public String viewAll(Model model) {
 * List<VendorEntity> allUsers = adminLoginService.getAllUsers();
 * model.addAttribute("users", allUsers); return "adminSuccess";
 * 
 * }
 * 
 * @PostMapping("/updateStatus") public String updateStatus(@RequestParam("id")
 * int id,Model model) { VendorEntity ent=
 * adminLoginService.updateStatusById(id); model.addAttribute("user", ent);
 * return "adminSuccess";
 * 
 * }
 * 
 * }
 */