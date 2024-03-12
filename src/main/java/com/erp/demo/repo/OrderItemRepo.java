package com.erp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
	
	List<OrderItem> getAllByOid(Integer oid);

}
