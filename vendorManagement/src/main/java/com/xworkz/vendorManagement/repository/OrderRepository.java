package com.xworkz.vendorManagement.repository;

import java.util.List;

import com.xworkz.vendorManagement.entity.OrderEntity;

public interface OrderRepository {

	public boolean save(OrderEntity orderEntity);
	
	List<OrderEntity> viewAllOrders(int vendorId);
	
}
