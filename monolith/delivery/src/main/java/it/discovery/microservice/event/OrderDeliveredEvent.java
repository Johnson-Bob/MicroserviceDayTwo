package it.discovery.microservice.event;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class OrderDeliveredEvent implements BaseEvent{
	private int orderId;

	private LocalDateTime deliveryDate;
}
