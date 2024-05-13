package com.xworkz.vendorManagement.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.vendorManagement.dto.OrderDTO;
import com.xworkz.vendorManagement.dto.ProductDTO;
import com.xworkz.vendorManagement.dto.VendorDTO;
import com.xworkz.vendorManagement.service.AdminService;
import com.xworkz.vendorManagement.service.OrderService;
import com.xworkz.vendorManagement.service.ProductService;
import com.xworkz.vendorManagement.service.VendorService;


@Component
@RequestMapping("/admin")
@Controller

public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@GetMapping("/adminLoginPage")
	public String adminPage() {
		return "admin";
	}

	@GetMapping("/adminLogin")
	public String findByEmailAndPassword(@RequestParam String email, String password, Model model,
			HttpSession session) {
		String userEmail = email;
		session.setAttribute("loggedInEmail", email);
		boolean find = adminService.findEmailAndPassword(userEmail, password);
		if (find) {
			System.out.println("Login success..");
			return "redirect:/admin/adminPortal";
		} else {
			System.out.println("Login failed...");

			return "admin";
		}
	}

	@GetMapping("/adminPortal")
	public String adminPortalPage() {
		return "adminPortal";
	}

	@GetMapping("/display")
	public void displayUserImageByEmail(HttpServletResponse response, @RequestParam String imagePath,
			HttpSession session) throws IOException {
		System.out.println("=================================");
		System.err.println("imagePath========================" + imagePath);
		File file = new File("D:\\vendorImage\\" + imagePath);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		response.flushBuffer();
	}

	@GetMapping("/viewVendorDetails")
	public ResponseEntity<List<VendorDTO>> viewVendorDetails() {
		List<VendorDTO> readAllVendorDetails = vendorService.getAllVendors();
		System.out.println("Vendors===" + readAllVendorDetails);
		if (readAllVendorDetails != null && !readAllVendorDetails.isEmpty()) {
			return ResponseEntity.ok(readAllVendorDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/viewproduct")
	public ResponseEntity<List<ProductDTO>> viewProductDetails() {
		List<ProductDTO> readAllProductDetails = productService.getAllProducts();
		System.out.println("Products===" + readAllProductDetails);
		if (readAllProductDetails != null && !readAllProductDetails.isEmpty()) {
			return ResponseEntity.ok(readAllProductDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/submitOrder")
	public ResponseEntity<String> saveOrders(@Valid OrderDTO dto) {
		boolean saved = orderService.validateAndSave(dto);
		if (saved) {
			return new ResponseEntity<>("Order placed Successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to place the order", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
