package it.discovery.microservice.notification;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Notification {
	private String recipient;

	private String email;

	private String title;

	private String text;
	
	private LocalDateTime created = LocalDateTime.now();
}
