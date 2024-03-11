package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Order;
import com.erp.demo.model.physical.OrderItem;
import com.erp.demo.repo.DispatchmentRepo;
import com.erp.demo.repo.OrderItemRepo;
import com.erp.demo.repo.OrderRepo;
import com.erp.demo.repo.ProductRepo;

@Service
public class OrderMgmt {
	
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	OrderItemRepo orderItemRepo;
	@Autowired
	DispatchmentRepo dispatchmentRepo;
	@Autowired
	ProductRepo productRepo;

	public List<Order> getAll() {
		return orderRepo.findAll();
	}

	public Optional<Order> getById(Integer id) {
		return orderRepo.findById(id);
	}
	
	public List<Order> getByMid(Integer mid) {
		return orderRepo.findAllByMid(mid);
	}

	public Optional<Order> create(Order order) {
		
		validateOrderItems(order);
		validateOrder(order);
		
		if (order != null) {
			validateDispatchment(order);
			validateTotal(order);
			
			if (order.getDispatchment() != null) {
				order.setDid(dispatchmentRepo.save(order.getDispatchment()).getDid());
			}
			
			orderItemRepo.saveAll(order.getOrderItems());
			order = orderRepo.save(order);
		}
		
		return Optional.ofNullable(order); 
		 
	}

	public Optional<Order> update(Order order) {

		if (orderRepo.existsById(order.getDid())) {
			return Optional.empty();
		}
		
		validateOrderItems(order);
		validateOrder(order);
		
		if (order != null) {
			validateDispatchment(order);
			validateTotal(order);
			
			if (order.getDispatchment() != null) {
				order.setDid(dispatchmentRepo.save(order.getDispatchment()).getDid());
			}
			
			orderItemRepo.saveAll(order.getOrderItems());
			order = orderRepo.save(order);
		}
		
		return Optional.ofNullable(order); 
	}

	public Optional<Order> delete(Integer id) {
		orderRepo.deleteById(id);
		return orderRepo.findById(id);
	}

	/**
	 * Validation
	 * @param orderItems
	 */
	private void validateOrderItems(Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		List<OrderItem> validatedOrderItems;
		// 驗證價格：
		orderItems.forEach(
				orderItem -> orderItem.setPrice(productRepo.findById(orderItem.getPid()).get().getPrice()));
		// 驗證數量： 
		orderItems.forEach(
				orderItem -> orderItem.setQuantity(
						Math.min(orderItem.getQuantity(), productRepo.findById(orderItem.getPid()).get().getInventory())));
		// 移除數量為 0 的 OrderItem：
		validatedOrderItems = orderItems.stream().filter(
				orderItem -> orderItem.getQuantity() > 0).collect(Collectors.toList());
		// 更新 Order 的 OrderItem 列表：
		order.setOrderItems(validatedOrderItems);
	}
	
	private void validateOrder(Order order) {
		if (order.getOrderItems().isEmpty()) {
			order = null;
		}
	}
	
	private void validateDispatchment(Order order) {
		Boolean requiresDispatchment = false;
		List<OrderItem> orderItems = order.getOrderItems();		
		requiresDispatchment = orderItems.stream().anyMatch(
				orderItem -> productRepo.findById(orderItem.getPid()).get().getShippingMethod().equals("派送"));
		if (!requiresDispatchment) {
			order.setDispatchment(null);
		}
	}
	
	private void validateTotal(Order order) {
		order.setTotal(0);
		order.getOrderItems().forEach(orderItem -> order.setTotal(order.getTotal() + orderItem.getPrice() * orderItem.getQuantity()));
	}
}
