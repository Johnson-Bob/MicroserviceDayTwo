package it.discovery.microservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import it.discovery.microservice.event.BaseEvent;
import it.discovery.microservice.event.bus.EventBus;
import it.discovery.microservice.event.bus.Listener;

@Component
public class KafkaEventBus implements EventBus {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void sendEvent(BaseEvent event) {
		kafkaTemplate.send("orders", event);
	}

	@Override
	public void subscribe(Listener listener) {

	}

	@KafkaListener(topics = "orders")
	public void listen(ConsumerRecord<?, ?> record) {
		System.out.println(record.value());
	}

}
