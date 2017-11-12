package it.discovery.microservice.notification;

import org.springframework.stereotype.Component;

@Component
public class NotificationSender {
	private NotificationRepository notificationRepository;

	public NotificationSender(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	public void sendNotification(Notification notification) {
		System.out.println("Sending notification ... " + notification.toString());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notificationRepository.save(notification);

		System.out.println("Notification sent");
	}
}
