package it.discovery.microservice.notification;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import it.discovery.microservice.event.OrderCancelledEvent;
import it.discovery.microservice.event.OrderDeliveredEvent;
import it.discovery.microservice.event.PaymentSuccessEvent;

@Service
public class NotificationService {
	private NotificationSender notificationSender;

	public NotificationService(NotificationSender notificationSender) {
		this.notificationSender = notificationSender;
	}
	
	@EventListener
	public void handle(OrderDeliveredEvent event) {
		Notification notification = new Notification();
//		notification.setEmail(order.getCustomer().getEmail());
//		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + event.getOrderId() + " is delivered");
		notification.setText("Hi/n. Your order has been delivered");
		
		notificationSender.sendNotification(notification);
	}
	
	@EventListener
	public void handle(OrderCancelledEvent event) {
		Notification notification = new Notification();
//		notification.setEmail(order.getCustomer().getEmail());
//		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + event.getOrderId() + " is canceled");
		notification.setText("Hi/n. Your order has been canceled");

		notificationSender.sendNotification(notification);
	}
	
	@EventListener
	public void handle(PaymentSuccessEvent event) {
		Notification notification = new Notification();
//		notification.setEmail(order.getCustomer().getEmail());
//		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + event.getOrderId() + " is completed");
		notification.setText("Hi/n. Your order has been completed");

		notificationSender.sendNotification(notification);
	}

}
