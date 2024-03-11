package com.erp.demo.service.external;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Product;
import com.erp.demo.repo.ProductRepo;

@Service
public class ProductSvc {

	@Autowired
	ProductRepo productRepo;
	
	public List<Product> getAll() {
		return productRepo.findAll();
	}

	public Optional<Product> getById(Integer id) {
		return productRepo.findById(id);
	}
	
	public List<Product> getByCategory(String category) {
		return productRepo.findAllByCategory(category); 
	}
	
}
