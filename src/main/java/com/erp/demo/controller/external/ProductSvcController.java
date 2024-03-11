package com.erp.demo.controller.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.Product;
import com.erp.demo.service.internal.ProductMgmt;

@RestController
@RequestMapping("/api/ex/v1/products")
public class ProductSvcController {
	
	@Autowired
	ProductMgmt productMgmt;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok().body(productMgmt.getAll());
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(productMgmt.getById(id));
	}
	
	@GetMapping(value = "/categories/{category}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") String category) {
		return ResponseEntity.ok().body(productMgmt.getByCategory(category));
	}
	
}
