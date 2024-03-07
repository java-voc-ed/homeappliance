package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
