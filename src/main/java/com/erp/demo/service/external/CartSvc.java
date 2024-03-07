package com.erp.demo.service.external;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.CartRepo;
import com.erp.demo.repo.ProductRepo;

public class CartSvc {

	@Autowired
	CartRepo cartRepo;
	@Autowired
	ProductRepo productRepo;
	
//	public Optional<Cart> getByMid() {
//		/**
//		 * TODO: Get member from Spring Security after its implementation.
//		 */
//		Member loggedInMember = new Member();
//		loggedInMember.setMid(999);
//		
//		return cartRepo.findById(loggedInMember.getMid());
//	}
//
//	public Boolean add(Integer pid, Integer quantity) {
//		/**
//		 * TODO: Get member from Spring Security after its implementation.
//		 */
//		Member loggedInMember = new Member();
//		loggedInMember.setMid(999);
//		
//		/**
//		 * TODO: SAVE USING REFLECTION.
//		 */
//		Optional<Cart> cart = cartRepo.findById(loggedInMember.getMid());
//		cart.getClass().getFields();
//		return true;
//	}
//	
//	public Optional<Cart> save(Cart cart) {
//		/**
//		 * TODO: Get member from Spring Security after its implementation.
//		 */
//		Member loggedInMember = new Member();
//		loggedInMember.setMid(999);
//		
//		return (loggedInMember.getMid() == cart.getMid())
//				? Optional.of(cartRepo.save(cart))
//				: Optional.empty();
//	}
//	
//	private void validate(Cart cart) {
//		
//		Cart
//		
//		/**
//		 * Validate Product:
//		 */
//		
//		
//		
//		
//		/**
//		 * Validate quantity:
//		 */
//		if (quantity == null) {
//			quantity = 1;
//		}
//		
//		if (quantity > 10) {
//			quantity = 10;
//		}
//		
//		Integer stock = productRepo.findById(pid).get(); 		
//		if (quantity > stock) {
//			quantity = stock;
//		}
//	}

}
