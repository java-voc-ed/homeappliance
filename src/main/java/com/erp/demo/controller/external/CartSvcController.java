package com.erp.demo.controller.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.Member;
import com.erp.demo.service.external.CartSvc;

@RestController
@RequestMapping("/api/ex/v1/carts")
public class CartSvcController {
	
	@Autowired
	CartSvc cartSvc;
	
//	@GetMapping
//	public ResponseEntity<Cart> getByMid() {
//		return ResponseEntity.ok(cartSvc.getByMid());
//	}
//	
//	@PostMapping(value = "/add")
//	public ResponseEntity<Cart> add(@RequestParam("pid") Integer pid, @RequestParam("quantity") Integer quantity) {
//		return (cartSvc.add(pid, quantity)) 
//				? ResponseEntity.ok()
//				: ResponseEntity.badRequest();
//		
//	}
//	
//	@PostMapping(value = "/save")
//	public ResponseEntity<Cart> save(@RequestBody Cart cart) {
//		return (cartSvc.save(cart));
//		
//	}
	
}
