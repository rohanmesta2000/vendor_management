package com.xworkz.vendorManagement.service;

import java.util.List;

import com.xworkz.vendorManagement.dto.OrderDTO;

public interface OrderService {
	
	boolean validateAndSave(OrderDTO orderDTO);
	
	List<OrderDTO> getAllOrdersByVendorId(int vendor_id);
	
	List<OrderDTO> getAllOrdersByEmail(String email);

}
