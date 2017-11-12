package it.discovery.microservice.payment;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import it.discovery.microservice.event.BaseEvent;
import it.discovery.microservice.event.OrderCompletedEvent;
import it.discovery.microservice.event.PaymentSuccessEvent;
import it.discovery.microservice.event.bus.EventBus;
import it.discovery.microservice.event.bus.Listener;

@Service
public class PaymentService implements Listener {
	//private EventBus eventBus = EventBus.getInstance();
	private EventBus eventBus;
	
	public PaymentService(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.subscribe(this);
	}

	@EventListener
	public void pay(OrderCompletedEvent event) {
		System.out.println("Charging " + event.getAmount()
		+ " from credit card " + event.getCardNumber()); 
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		eventBus.sendEvent(
				new PaymentSuccessEvent(event.getOrderId()));
		System.out.println("Charging completed");
	}

	@Override
	public void accept(BaseEvent event) {
		if(event instanceof OrderCompletedEvent) {
			OrderCompletedEvent completedEvent = 
					(OrderCompletedEvent) event;
			pay(completedEvent);
		}
	}

}
