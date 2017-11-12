package it.discovery.microservice.order;

import java.util.List;

public interface OrderRepository {
	
	void save(Order order);

	Order findById(int orderId);
	
	List<Order> findOrders();
}
