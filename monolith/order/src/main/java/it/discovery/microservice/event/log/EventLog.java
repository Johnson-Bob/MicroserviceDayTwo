package it.discovery.microservice.event.log;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

@Table(name = "EVENT_LOG")
@Entity
@Setter
public class EventLog {

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
}
