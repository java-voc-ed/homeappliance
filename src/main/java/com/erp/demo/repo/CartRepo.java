package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;

public interface CartRepo extends JpaRepository<CartItem, Integer> {

}
