package it.discovery.microservice.event;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Value
public class PaymentSuccessEvent extends ApplicationEvent implements BaseEvent {
	
	private int orderId;

	public PaymentSuccessEvent(int orderId) {
		super("");
		this.orderId = orderId;
	}
}
