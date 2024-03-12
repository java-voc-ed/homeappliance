package com.erp.demo.service.external;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.erp.demo.model.authentication.MemberUserDetails;
import com.erp.demo.model.physical.OrderDetail;
import com.erp.demo.model.physical.OrderItem;
import com.erp.demo.repo.DispatchmentRepo;
import com.erp.demo.repo.OrderDetailRepo;
import com.erp.demo.repo.OrderItemRepo;
import com.erp.demo.repo.ProductRepo;

@Service
public class OrderSvc {

	@Autowired
	OrderDetailRepo orderDetailRepo;
	@Autowired
	OrderItemRepo orderItemRepo;
	@Autowired
	DispatchmentRepo dispatchmentRepo;
	@Autowired
	ProductRepo productRepo;
	//getLoggedInMember().getMid()
	public List<OrderDetail> getByMid() {
		List<OrderDetail> orderDetailList = orderDetailRepo.findAllByMid(1);
		orderDetailList.forEach(orderDetail 
				-> orderDetail.setOrderItems(orderItemRepo.getAllByOid(orderDetail.getOid())));
		return orderDetailList;
	}

	
	/**
	 * JSON 寫法：
	 * {
        "recipient": "許怡如",
        "cellphone": "0984849465",
        "landlineprefix": "02",
        "landline": "12345678",
        "email": "user1@example.com",
        "postalCode": 100,
        "address": "台北市信義區忠孝東路一段43號",
        "footnote": null,
        "orderItems": [
            {
                "id": 1,
                "oid": 1,
                "pid": 1,
                "price": 1000,
                "quantity": 1
            }
        ],
	 * 
	 * }
	 * @param orderDetail
	 * @return
	 */
	
	public Optional<OrderDetail> create(OrderDetail orderDetail) {
		
		orderDetail.setOid(null);
		orderDetail.setMid(getLoggedInMember().getMid());
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
	
	/**
	 * Authentication
	 */
	private MemberUserDetails getLoggedInMember() {
		return (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
