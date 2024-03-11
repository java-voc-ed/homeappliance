package com.erp.demo.controller.internal;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.Product;
import com.erp.demo.service.internal.ProductMgmt;

@RestController
@RequestMapping("/api/in/v1/products")
public class ProductMgmtController {

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
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody Product product) {
		Optional<Product> createdProduct = productMgmt.create(product);
		return (createdProduct.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/products/" + createdProduct.get().getPid())).build()
				: ResponseEntity.badRequest().body("Product already exists.");
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return (id.equals(product.getPid()) && productMgmt.update(product).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/products/" + id)).build()
				: ResponseEntity.badRequest().body("Product does not exist.");

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		Optional<Product> deletedProduct = productMgmt.delete(id);
		return (deletedProduct.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("Product does not exist.");
	}
}
