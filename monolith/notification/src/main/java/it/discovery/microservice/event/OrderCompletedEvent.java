package it.discovery.microservice.event;

import org.springframework.context.ApplicationEvent;

import lombok.Value;

@Value
public class OrderCompletedEvent extends ApplicationEvent implements BaseEvent {
	private int orderId;

	private String cardNumber;

	private double amount;

	public OrderCompletedEvent(int orderId, String cardNumber, double amount) {
		super("");
		this.orderId = orderId;
		this.cardNumber = cardNumber;
		this.amount = amount;
	}

}
