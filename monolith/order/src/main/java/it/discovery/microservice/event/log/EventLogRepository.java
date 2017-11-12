package it.discovery.microservice.event.log;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends 
JpaRepository<EventLog, Integer> {
	Stream<EventLog> findByEntityId(int entityId);

}
