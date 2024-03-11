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
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Product> getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Product> getByCategory(String category) {
		// TODO Auto-generated method stub
		productRepo.findAllByCategory(category);
		return null;
	}

	public Optional<Product> create(Product product) {
		// TODO Auto-generated method stub
		// 存入前請檢查唯一值 SKU 是否有重複。 
		return null;
	}

	public Optional<Product> update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Product> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
