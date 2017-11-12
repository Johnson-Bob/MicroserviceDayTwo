package it.discovery.microservice.event;

import lombok.Value;

@Value
public class OrderPayedEvent implements BaseEvent{
	private int orderId;
	
	private String cardNumber;
	
	private double amount;
}
