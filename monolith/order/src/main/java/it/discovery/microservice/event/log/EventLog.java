package it.discovery.microservice.event.log;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.discovery.microservice.event.BaseEvent;
import lombok.Setter;

//@Table(name = "EVENT_LOG")
//@Entity
@Setter
@Document
public class EventLog {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Id
	private int id;

	private LocalDateTime created;

	private int entityId;

	private String eventClass;

	private String source;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	//@Column(name = "EVENT_SOURCE", updatable = false, nullable = false)
	public String getSource() {
		return source;
	}

	public int getEntityId() {
		return entityId;
	}

	//@Column(name = "EVENT_CLASS", nullable = false)
	public String getEventClass() {
		return eventClass;
	}

	@Transient
	public BaseEvent getEvent() {
		try {
			return (BaseEvent) MAPPER.readValue(source, Class.forName(eventClass));
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static EventLog fromEvent(BaseEvent event) {
		EventLog log = new EventLog();
		log.setEventClass(event.getClass().getName());
		try {
			log.setSource(MAPPER.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return log;
	}
	
	@PrePersist
	public void preSave() {
		created = LocalDateTime.now();
	}
}
