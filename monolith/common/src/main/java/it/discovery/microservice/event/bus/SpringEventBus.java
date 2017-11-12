package it.discovery.microservice.event.bus;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import it.discovery.microservice.event.BaseEvent;

@Component
public class SpringEventBus implements EventBus {

	private final ApplicationEventPublisher publisher;

	private final ApplicationEventMulticaster multicaster;

	public SpringEventBus(ApplicationEventPublisher publisher, ApplicationEventMulticaster multicaster) {
		this.publisher = publisher;
		this.multicaster = multicaster;
	}

	@Override
	public void sendEvent(BaseEvent event) {
		publisher.publishEvent(event);
	}

	@Override
	public void subscribe(Listener listener) {
		multicaster.addApplicationListener(event -> 
		listener.accept((BaseEvent) event));
	}

}
