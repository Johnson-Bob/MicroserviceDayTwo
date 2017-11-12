package it.discovery.microservice.event.bus;

import java.util.ArrayList;
import java.util.List;

import it.discovery.microservice.event.BaseEvent;

public class SyncEventBus implements EventBus {

	private final List<Listener> listeners = new ArrayList<>();
	
	public static final EventBus INSTANCE = new SyncEventBus();

	private SyncEventBus() {
	}

	@Override
	public void sendEvent(BaseEvent event) {
		listeners.forEach(item -> item.accept(event));
	}

	@Override
	public void subscribe(Listener listener) {
		listeners.add(listener);
	}

}
