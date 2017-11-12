package it.discovery.microservice.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import it.discovery.microservice.customer.CustomerRepository;
import it.discovery.microservice.event.BaseEvent;
import it.discovery.microservice.event.OrderCompletedEvent;
import it.discovery.microservice.event.OrderDeliveredEvent;
import it.discovery.microservice.event.PaymentSuccessEvent;
import it.discovery.microservice.event.bus.EventBus;
import it.discovery.microservice.event.bus.Listener;
import it.discovery.microservice.event.log.EventLog;
import it.discovery.microservice.event.log.EventLogRepository;
import it.discovery.microservice.order.commands.CreateOrderCommand;

@Service
public class OrderService implements Listener {
	private final OrderRepository orderRepository;

	private CustomerRepository customerRepository;

	private EventBus eventBus;

	private EventLogRepository eventLogRepository;

	@Autowired
	private RedisTemplate<Integer, Object> redisTemplate;

	public OrderService(OrderRepository orderRepository, EventBus eventBus, CustomerRepository customerRepository,
			EventLogRepository eventLogRepository) {
		this.orderRepository = orderRepository;
		this.eventBus = eventBus;
		this.customerRepository = customerRepository;
		this.eventLogRepository = eventLogRepository;
		eventBus.subscribe(this);
	}

	public void complete(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			eventBus.sendEvent(
					new OrderCompletedEvent(order.getId(), order.getCustomer().getCardNumber(), order.getAmount()));
		}
	}

	public void cancel(int orderId) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			order.setCancelled(true);
			orderRepository.save(order);

		}
	}

	public Order createOrder(CreateOrderCommand cmd) {
		Order order = new Order();
		order.setCustomer(customerRepository.findById(cmd.getCustomerId()));
		eventLogRepository.saveAll(order.process(cmd)
				.stream().map(EventLog::fromEvent)
				.collect(Collectors.toList()));

		return order;
	}

	public void addBook(int orderId, int bookId, double price, int number) {
		Order order = orderRepository.findById(orderId);
		if (order != null) {
			order.addItem(new OrderItem(bookId, price, number));
			orderRepository.save(order);
		}
	}

	public void save(Order order) {
		orderRepository.save(order);
	}

	public List<Order> findOrders() {
		return orderRepository.findOrders();
	}

	@EventListener
	public void pay(PaymentSuccessEvent event) {
		Order order = orderRepository.findById(event.getOrderId());
		order.setPayed(true);
		orderRepository.save(order);
	}

	@EventListener
	public void delivered(OrderDeliveredEvent event) {
		Order order = orderRepository.findById(event.getOrderId());
		order.setDelivered(true);
		order.setDeliveryDate(event.getDeliveryDate());
		orderRepository.save(order);
	}

	public Order findOrder(int orderId) {
		Order order = new Order();
		eventLogRepository.findByEntityId(orderId).map(EventLog::getEvent);

		Object val =  redisTemplate.boundValueOps(orderId).get();
		return (Order) val;
	}

	@Override
	public void accept(BaseEvent event) {
		// TODO Auto-generated method stub

	}

}
