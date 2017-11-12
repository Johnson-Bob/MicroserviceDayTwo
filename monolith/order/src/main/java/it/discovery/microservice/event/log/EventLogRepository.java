package it.discovery.microservice.event.log;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventLogRepository extends 
MongoRepository<EventLog, Integer> {
	Stream<EventLog> findByEntityId(int entityId);

}
