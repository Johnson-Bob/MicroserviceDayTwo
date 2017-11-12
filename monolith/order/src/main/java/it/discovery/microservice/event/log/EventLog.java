package it.discovery.microservice.event.log;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.discovery.microservice.event.BaseEvent;
import lombok.Setter;

@Table(name = "EVENT_LOG")
@Entity
@Setter
public class EventLog {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private int id;

	private LocalDateTime created;

	private int entityId;

	private String eventClass;

	private String source;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	@Column(name = "EVENT_SOURCE", updatable = false, nullable = false)
	public String getSource() {
		return source;
	}

	public int getEntityId() {
		return entityId;
	}

	@Column(name = "EVENT_CLASS", nullable = false)
	public String getEventClass() {
		return eventClass;
	}
	
	@Transient
	public BaseEvent getEvent() {
		try {
			return (BaseEvent) MAPPER.readValue(source, 
					Class.forName(eventClass));
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
