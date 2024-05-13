package com.xworkz.vendorManagement.service;

import java.util.List;

import com.xworkz.vendorManagement.dto.ProductDTO;

public interface ProductService {
	public boolean validateAndSave(ProductDTO addProductDTO);

	
	List<ProductDTO> getAllProducts();
	
	List<ProductDTO> getAllProductsOfLoginVendor (int vendor_id);
	
	List<ProductDTO> getAllProductDetailsByVendorId(String email);

	//update	
    ProductDTO findAllProductEntityById(int id) ;
	
	ProductDTO updateProductById(ProductDTO productDTO, int id);
}