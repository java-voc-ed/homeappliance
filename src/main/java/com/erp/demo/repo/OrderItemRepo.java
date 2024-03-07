package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

}
