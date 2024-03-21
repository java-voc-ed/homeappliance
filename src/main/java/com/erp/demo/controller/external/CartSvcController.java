package com.erp.demo.controller.external;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.service.external.CartSvc;

// TODO: FOR TESTING PURPOSES ONLY!
@CrossOrigin
@RestController
@RequestMapping("/api/ex/v1/carts")
public class CartSvcController {
	
	@Autowired
	CartSvc cartSvc;
	
	/**
	 * CRUD Operation
	 */
	
	/**
	 * 開啟購物車列表時呼叫。
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<CartItem>> getAll() {
		return ResponseEntity.ok(cartSvc.getAll());
	}
	
	/**
	 * 完成對單一 CartItem 變更後呼叫。
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<CartItem> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(cartSvc.getById(id));
	}
	
	/**
	 * 從商品頁添加單一 Product 到購物車時呼叫。
	 * @param pid
	 * @param quantity
	 * @return
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestParam("pid") Integer pid, @RequestParam("quantity") Integer quantity) {
		Optional<CartItem> cartItem = cartSvc.add(pid, quantity);
		return (cartItem.isPresent()) 
				? ResponseEntity.created(URI.create("/api/ex/v1/carts" + cartItem.get().getId())).build()
				: ResponseEntity.badRequest().body("購物車內商品種類已達上限。");		
	}
	
	/**
	 * 從購物車頁上對單一 CartItem 作變更時呼叫。
	 * @param cartToSave
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody CartItem cartItemToUpdate) {
		Optional<CartItem> updatedCartItem = cartSvc.update(cartItemToUpdate);
		return (id == cartItemToUpdate.getId()
				&& updatedCartItem.isPresent()) 
				? ResponseEntity.noContent().location(URI.create("/api/ex/v1/carts" + id)).build()
				: ResponseEntity.badRequest().body("更新失敗。");		
	}
	
	/**
	 * 移除購物車內所有 CartItems。
	 * @return
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteAll() {
		return (cartSvc.deleteAll().isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("移除失敗");
	}
	
	/**
	 * 移除個別 CartItem。
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
		Optional<CartItem> deletedCartItem = cartSvc.deleteById(id);
		return (deletedCartItem.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("移除失敗");
	}	
		
}
