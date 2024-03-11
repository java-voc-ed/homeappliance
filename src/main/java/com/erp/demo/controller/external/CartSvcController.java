package com.erp.demo.controller.external;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.service.external.CartSvc;

@RestController
@RequestMapping("/api/ex/v1/carts")
public class CartSvcController {
	
	@Autowired
	CartSvc cartSvc;
	
	@GetMapping
	public ResponseEntity<List<CartItem>> getByMid() {
		return ResponseEntity.ok(cartSvc.getByMid());
	}
	
	/**
	 * 從商品頁添加單一商品時呼叫。
	 * @param pid
	 * @param quantity
	 * @return
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestParam("pid") Integer pid, @RequestParam("quantity") Integer quantity) {
		Optional<CartItem> cartItem = cartSvc.add(pid, quantity);
		return (cartItem.isPresent()) 
				? ResponseEntity.created(URI.create("/api/ex/v1/carts")).build()
				: ResponseEntity.badRequest().body("Product does not exist.");		
	}
	
	/**
	 * 從購物車頁上作變更時呼叫。
	 * @param cartToSave
	 * @return
	 */
	
//	@PutMapping(value = "/save")
//	public ResponseEntity<String> save(@RequestBody List<CartItem> cartToSave) {
//		List<CartItem> savedCart = cartSvc.save(cartToSave);
//		return (true) 
//				? ResponseEntity.noContent().location(URI.create("/api/ex/v1/carts")).build()
//				: ResponseEntity.badRequest().body("Product does not exist.");		
//	}
	
}
