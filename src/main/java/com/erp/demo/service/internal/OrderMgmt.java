package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Order;
import com.erp.demo.repo.OrderRepo;

@Service
public class OrderMgmt {
	
	@Autowired
	OrderRepo orderRepo;

	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Order> getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Order> getByMid(Integer mid) {
		// TODO Auto-generated method stub
		orderRepo.findAllByMid(mid);
		return null;
	}

	public Optional<Order> create(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Order> update(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Order> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



}
