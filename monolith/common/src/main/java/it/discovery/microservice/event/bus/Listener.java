package it.discovery.microservice.event.bus;

import it.discovery.microservice.event.BaseEvent;

public interface Listener {
	void accept(BaseEvent event);
}
