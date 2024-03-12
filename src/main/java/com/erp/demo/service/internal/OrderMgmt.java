package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.OrderDetail;
import com.erp.demo.model.physical.OrderItem;
import com.erp.demo.repo.DispatchmentRepo;
import com.erp.demo.repo.OrderItemRepo;
import com.erp.demo.repo.OrderDetailRepo;
import com.erp.demo.repo.ProductRepo;

@Service
public class OrderMgmt {
	
	@Autowired
	OrderDetailRepo orderDetailRepo;
	@Autowired
	OrderItemRepo orderItemRepo;
	@Autowired
	DispatchmentRepo dispatchmentRepo;
	@Autowired
	ProductRepo productRepo;

	public List<OrderDetail> getAll() {
		return orderDetailRepo.findAll();
	}

	public Optional<OrderDetail> getById(Integer id) {
		return orderDetailRepo.findById(id);
	}
	
	public List<OrderDetail> getByMid(Integer mid) {
		return orderDetailRepo.findAllByMid(mid);
	}

	public Optional<OrderDetail> create(OrderDetail orderDetail) {
		
		orderDetail.setOid(null);
		
		validateOrderItems(orderDetail);
		validateOrder(orderDetail);
		
		if (orderDetail != null) {
			validateDispatchment(orderDetail);
			validateTotal(orderDetail);
			
			if (orderDetail.getDispatchment() != null) {
				orderDetail.setDid(dispatchmentRepo.save(orderDetail.getDispatchment()).getDid());
			}
			
			orderDetail = orderDetailRepo.save(orderDetail);
			Integer oid = orderDetail.getOid();
			orderDetail.getOrderItems().forEach(orderItem -> orderItem.setOid(oid));
			orderItemRepo.saveAll(orderDetail.getOrderItems());
		}
		
		return Optional.ofNullable(orderDetail); 
		 
	}

	public Optional<OrderDetail> update(OrderDetail orderDetail) {

		if (orderDetailRepo.existsById(orderDetail.getDid())) {
			return Optional.empty();
		}
		
		validateOrderItems(orderDetail);
		validateOrder(orderDetail);
		
		if (orderDetail != null) {
			validateDispatchment(orderDetail);
			validateTotal(orderDetail);
			
			if (orderDetail.getDispatchment() != null) {
				orderDetail.setDid(dispatchmentRepo.save(orderDetail.getDispatchment()).getDid());
			}
			
			orderItemRepo.saveAll(orderDetail.getOrderItems());
			orderDetail = orderDetailRepo.save(orderDetail);
		}
		
		return Optional.ofNullable(orderDetail); 
	}

	public Optional<OrderDetail> delete(Integer id) {
		orderDetailRepo.deleteById(id);
		return orderDetailRepo.findById(id);
	}

	/**
	 * Validation
	 * @param orderItems
	 */
	private void validateOrderItems(OrderDetail orderDetail) {
		List<OrderItem> orderItems = orderDetail.getOrderItems();
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
		orderDetail.setOrderItems(validatedOrderItems);
	}
	
	private void validateOrder(OrderDetail orderDetail) {
		if (orderDetail.getOrderItems().isEmpty()) {
			orderDetail = null;
		}
	}
	
	private void validateDispatchment(OrderDetail order) {
		Boolean requiresDispatchment = false;
		List<OrderItem> orderItems = order.getOrderItems();		
		requiresDispatchment = orderItems.stream().anyMatch(
				orderItem -> productRepo.findById(orderItem.getPid()).get().getShippingMethod().equals("派送"));
		if (!requiresDispatchment) {
			order.setDispatchment(null);
		}
	}
	
	private void validateTotal(OrderDetail order) {
		order.setTotal(0);
		order.getOrderItems().forEach(orderItem -> order.setTotal(order.getTotal() + orderItem.getPrice() * orderItem.getQuantity()));
	}
}
