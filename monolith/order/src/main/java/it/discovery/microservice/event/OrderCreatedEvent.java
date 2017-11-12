package it.discovery.microservice.event;

import java.time.LocalDateTime;
import java.util.List;

import it.discovery.microservice.order.OrderItem;
import lombok.Value;

@Value
public class OrderCreatedEvent implements BaseEvent {
	private List<OrderItem> items;
	
	private LocalDateTime orderDate;
}
