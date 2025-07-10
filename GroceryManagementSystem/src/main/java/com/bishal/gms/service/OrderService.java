package com.bishal.gms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bishal.gms.dto.OrderRequest;
import com.bishal.gms.entity.Order;
import com.bishal.gms.repo.OrderRepository;

@Service
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
		
	}

	public Order createOrder(OrderRequest request) {
	        Order order = new Order();
	        order.setCustomerEmail(request.getCustomerEmail());
	        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
			return orderRepository.findAll();
    }
}
