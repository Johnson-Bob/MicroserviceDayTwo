package it.discovery.microservice.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryNotificationRepository implements
NotificationRepository{
	private final List<Notification> notifications = 
			new ArrayList<>();

	@Override
	public void save(Notification notification) {
		notifications.add(notification);
	}

}
