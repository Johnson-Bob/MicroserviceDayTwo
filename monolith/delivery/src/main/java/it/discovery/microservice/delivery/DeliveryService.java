package it.discovery.microservice.delivery;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import it.discovery.microservice.event.OrderDeliveredEvent;
import it.discovery.microservice.event.OrderPayedEvent;
import it.discovery.microservice.event.bus.EventBus;

@Service
public class DeliveryService {
	private final EventBus eventBus;
	
	public DeliveryService(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@EventListener
	public void deliver(OrderPayedEvent event) {
		System.out.println("Order " + event.getOrderId() +
				" delivered");
		
		eventBus.sendEvent(new OrderDeliveredEvent(event.getOrderId(),
				LocalDateTime.now()));
	}

}
