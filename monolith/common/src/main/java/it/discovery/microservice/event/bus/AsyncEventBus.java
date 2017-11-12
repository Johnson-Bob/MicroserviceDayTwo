package it.discovery.microservice.event.bus;

import java.util.ArrayList;
import java.util.List;

import it.discovery.microservice.event.BaseEvent;

public class AsyncEventBus implements EventBus {

	public static final EventBus INSTANCE = new AsyncEventBus();
	
	private final List<Listener> 
	listeners = new ArrayList<>();

	private AsyncEventBus() {
	}

	@Override
	public void sendEvent(BaseEvent event) {
		listeners.parallelStream()
			.forEach(item -> item.accept(event));
	}

	@Override
	public void subscribe(Listener listener) {
		listeners.add(listener);
	}
}
