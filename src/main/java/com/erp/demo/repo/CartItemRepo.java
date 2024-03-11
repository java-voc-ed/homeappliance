package com.erp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

	public List<CartItem> findAllByMid(Integer mid);

	public void deleteAllByMid(Integer mid);

}
