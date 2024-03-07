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

import com.erp.demo.model.physical.Order;
import com.erp.demo.service.internal.OrderMgmt;
import com.erp.demo.service.internal.ProductMgmt;

@RestController
@RequestMapping("/api/in/v1/orders")
public class OrderMgmtController {
	
	@Autowired
	OrderMgmt orderMgmt;
	
	@GetMapping
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok().body(orderMgmt.getAll());
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> getById(@PathVariable("id") String id) {
		return ResponseEntity.of(orderMgmt.getById(id));
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody Order order) {
		Optional<Order> createdOrder = orderMgmt.create(order);
		return (createdOrder.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/orders/" + createdOrder.get().getOid())).build()
				: ResponseEntity.badRequest().body("Order already exists.");
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody Order order) {
		return (id.equalsIgnoreCase(order.getOid().toString()) && orderMgmt.update(order).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/orders/" + id)).build()
				: ResponseEntity.badRequest().body("Order does not exist.");

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		Optional<Order> deletedOrder = orderMgmt.delete(id);
		return (deletedOrder.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("Order does not exist.");
	}

}
