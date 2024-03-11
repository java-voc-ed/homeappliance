package com.erp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {

	List<OrderDetail> findAllByMid(Integer mid);

}
