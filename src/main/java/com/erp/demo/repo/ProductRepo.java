package com.erp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	List<Product> findAllByCategory(String category);

	Boolean existsBySku(String sku);
	
}
