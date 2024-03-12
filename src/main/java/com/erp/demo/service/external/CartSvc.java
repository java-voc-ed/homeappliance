package com.erp.demo.service.external;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.erp.demo.model.authentication.MemberUserDetails;
import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.CartItemRepo;
import com.erp.demo.repo.ProductRepo;

@Service
public class CartSvc {

	@Autowired
	CartItemRepo cartItemRepo;
	@Autowired
	ProductRepo productRepo;
	
	/**
	 * CRUD Operation
	 */
	
	public List<CartItem> getAll() {
		MemberUserDetails loggedInMember = getLoggedInMember();
		return cartItemRepo.findAllByMid(loggedInMember.getMid());
	}
	
	public Optional<CartItem> getById(Integer id) {
		MemberUserDetails loggedInMember = getLoggedInMember();
		Optional<CartItem> cartItem = cartItemRepo.findById(id);
		return (cartItem.isPresent() 
				&& cartItem.get().getMid() == loggedInMember.getMid())
				? cartItem
				: Optional.empty();
	}			

	public Optional<CartItem> add(Integer pid, Integer quantity) {
		MemberUserDetails loggedInMember = getLoggedInMember();		
		List<CartItem> cart = cartItemRepo.findAllByMid(loggedInMember.getMid());
		CartItem addedCartItem;
		
		/**
		 * 輪詢若該會員的購物車：
		 * 若其內已有同款商品的 CartItem，則...
		 * 1. 直接在該筆 CartItem 紀錄上進行更新、
		 * 2. 該筆 CartItem 待購數量 + 1 至該款商品庫存為限、
		 * 3. 回傳該筆 CartItem 給 Controller。
		 */
		for (CartItem cartItem : cart) {
			if (cartItem.getPid() == pid) {
				addedCartItem = cartItem;				
				cartItem.setQuantity(validateQuantity((cartItem.getQuantity() + 1), pid));
				return Optional.ofNullable(addedCartItem);
			}
		}
		
		/**
		 * 若該會員的購物車內，查無同款商品的 CartItem，則...
		 * 確認該會員購物車內商品種類是否已達上限（10），若否，則...
		 * 1. 創建一筆新的 CartItem 紀錄、
		 * 2. 回傳該筆 CartItem 給 Controller。
		 */
		if (cart.size() <= 10) {
			cartItemRepo.save(addedCartItem = new CartItem(-1, loggedInMember.getMid(), pid, validateQuantity(1, pid)));
			return Optional.ofNullable(addedCartItem);	
		} else {
			return Optional.empty();
		}
		
	}
	
	public Optional<CartItem> update(CartItem cartItemToUpdate) {
		MemberUserDetails loggedInMember = getLoggedInMember();
		return (cartItemToUpdate.getMid() == loggedInMember.getMid())
				? Optional.of(cartItemRepo.save(cartItemToUpdate))
				: Optional.empty();
	}
	
	public List<CartItem> deleteAll() {
		MemberUserDetails loggedInMember = getLoggedInMember();
		cartItemRepo.deleteAllByMid(loggedInMember.getMid());
		return cartItemRepo.findAllByMid(loggedInMember.getMid());
	}
	

	public Optional<CartItem> deleteById(Integer id) {
		MemberUserDetails loggedInMember = getLoggedInMember();
		Optional<CartItem> cartItem = cartItemRepo.findById(id);
		if (cartItem.isPresent() 
			&& cartItem.get().getMid() == loggedInMember.getMid()) {
			cartItemRepo.deleteById(id);
		}
		return cartItemRepo.findById(id);
	}

	/**
	 * Validation
	 */

	private Integer validateQuantity(Integer quantity, Integer pid) {
		return Math.min(quantity, productRepo.findById(pid).get().getInventory());
	}
	
	/**
	 * Authentication
	 */
	private MemberUserDetails getLoggedInMember() {
		return (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
