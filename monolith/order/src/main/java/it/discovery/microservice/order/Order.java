package it.discovery.microservice.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.discovery.microservice.customer.Customer;
import it.discovery.microservice.event.BaseEvent;
import it.discovery.microservice.event.OrderCreatedEvent;
import it.discovery.microservice.order.commands.CreateOrderCommand;
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
	
	public List<BaseEvent> process(CreateOrderCommand cmd) {
		return Arrays.asList(new OrderCreatedEvent(
				Arrays.asList(new OrderItem(cmd.getBookId(),
						cmd.getPrice(), cmd.getNumber())),
						LocalDateTime.now()));
	}
	
	public void apply(OrderCreatedEvent event) {
		this.items = event.getItems();
		this.orderDate = event.getOrderDate();
	}
	
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
