package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
}
