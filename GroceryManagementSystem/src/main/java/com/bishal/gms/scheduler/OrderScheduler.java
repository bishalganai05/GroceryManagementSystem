package com.bishal.gms.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bishal.gms.entity.Order;
import com.bishal.gms.repo.OrderRepository;

@Service
public class OrderScheduler {
	private final OrderRepository orderRepository;

	public OrderScheduler(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Scheduled(cron = "0 */2 * ? * *")
	public void processPendingOrders() {
        System.out.println("Processing Orders");
        List<Order> orders = orderRepository.findByStatus("PENDING");
        orders.forEach(order -> {
            order.setStatus("COMPLETED");
            System.out.println(order.getCustomerEmail());
            orderRepository.save(order);
        });
        System.out.println("Processed pending orders"+ orders.size());
    }
}
