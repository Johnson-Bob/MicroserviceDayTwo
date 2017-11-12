package it.discovery.microservice.event.bus;

import it.discovery.microservice.event.BaseEvent;

public interface EventBus {
	
	public static EventBus getInstance() {
		return AsyncEventBus.INSTANCE;
	}

	void sendEvent(BaseEvent event);
	
	void subscribe(Listener listener);

}
