package it.discovery.microservice.event.log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends 
JpaRepository<Integer, EventLog> {

}
