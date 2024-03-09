package com.erp.demo.service.external;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.Member;
import com.erp.demo.repo.CartRepo;
import com.erp.demo.repo.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class CartSvc {

	@Autowired
	CartRepo cartRepo;
	@Autowired
	ProductRepo productRepo;
	
	public List<CartItem> getByMid() {
		/**
		 * TODO: Get member from Spring Security after its implementation.
		 */
		Member loggedInMember = new Member();
		loggedInMember.setMid(1);
		
		return cartRepo.findAllByMid(loggedInMember.getMid());
	}

	public Optional<CartItem> add(Integer pid, Integer quantity) {
		/**
		 * TODO: Get member from Spring Security after its implementation.
		 */
		Member loggedInMember = new Member();
		loggedInMember.setMid(999);
		
		List<CartItem> cart = cartRepo.findAllByMid(loggedInMember.getMid());
		Integer productInventory = productRepo.findById(pid).get().getInventory();
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
				cartItem.setQuantity(Math.min((cartItem.getQuantity() + 1), productInventory));
				return Optional.ofNullable(addedCartItem);
			}
		}
		/**
		 * 若該會員的購物車內，查無同款商品的 CartItem，則...
		 * 1. 創建一筆新的 CartItem 紀錄、
		 * 2. 回傳該筆 CartItem 給 Controller。
		 */
		cartRepo.save(addedCartItem = new CartItem(-1, loggedInMember.getMid(), pid, Math.min(1, productInventory)));
		return Optional.ofNullable(addedCartItem);
	}
	
//	@Transactional
//	public List<CartItem> save(List<CartItem> cartToSave) {
//		/**
//		 * TODO: Get member from Spring Security after its implementation.
//		 */
//		Member loggedInMember = new Member();
//		loggedInMember.setMid(999);
//		
//		
//		return (true)
//				? cartToSave
//				: getByMid();
//	}
//	
//	private void validate(CartItem cartItem) {
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
