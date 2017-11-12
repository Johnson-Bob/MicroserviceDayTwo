package it.discovery.microservice.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOrderRepository implements OrderRepository{

	private final Map<Integer, Order> orders = new HashMap<>();

	private int counter;
	
	@Override
	public void save(Order order) {
		if(order.getId() == 0) {
			order.setId(++counter);
			orders.put(order.getId(), order);			
		} else {
			orders.put(order.getId(), order);
		}	
	}

	@Override
	public Order findById(int orderId) {
		return orders.get(orderId);
	}

	@Override
	public List<Order> findOrders() {
		return new ArrayList<>(orders.values());
	}

}
