package it.discovery.microservice.event;

import lombok.Value;

@Value
public class OrderCancelledEvent implements BaseEvent {
	private int orderId;

}
