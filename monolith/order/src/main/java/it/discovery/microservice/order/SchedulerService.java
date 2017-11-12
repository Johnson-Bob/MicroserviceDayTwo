package it.discovery.microservice.order;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class SchedulerService {
	private OrderRepository orderRepository;

	@Scheduled(fixedDelay = 30 * 60 * 1000)
	public void run() {
		orderRepository.findOrders().stream().filter(order -> !order.isPayed()).forEach(order -> {
//			Notification notification = new Notification();
//			notification.setEmail(order.getCustomer().getEmail());
//			notification.setRecipient(order.getCustomer().getName());
//			notification.setTitle("Don't forget to pay order " + order.getId());
//			notification.setText("Please, pay your outstaning order " + order.getId());
//
//			notificationService.sendNotification(notification);
		});
	}

}
