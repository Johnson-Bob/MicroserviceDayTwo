package it.discovery.microservice.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.discovery.microservice.customer.Customer;
import lombok.Data;

@Data
public class Order {
	private int id;
	
	private List<OrderItem> items;
	
	private LocalDateTime orderDate;
	
	private Customer customer;
	
	private boolean payed;
	
	private boolean delivered;
	
	private boolean cancelled;
	
	private LocalDateTime deliveryDate;
	
	private double deliveryPrice;
	
	public double getAmount() {
		return items.stream().mapToDouble(item -> item.getPrice() * item.getNumber()).sum();
	}

	public void addItem(OrderItem item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		items.add(item);		
	}

}
