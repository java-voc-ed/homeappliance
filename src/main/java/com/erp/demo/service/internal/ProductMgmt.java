package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Product;
import com.erp.demo.repo.OrderRepo;
import com.erp.demo.repo.ProductRepo;

@Service
public class ProductMgmt {

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

	public Optional<Product> create(Product product) {
		product.setPid(null);
		return (!productRepo.existsBySku(product.getSku()))
				? Optional.of(productRepo.save(product))
				: Optional.empty();
	}

	public Optional<Product> update(Product product) {
		return  (productRepo.existsById(product.getPid()))
				? Optional.of(productRepo.save(product))
				: Optional.empty();
	}

	public Optional<Product> delete(Integer id) {
		productRepo.deleteById(id);
		return productRepo.findById(id);		
	}	
	
}
