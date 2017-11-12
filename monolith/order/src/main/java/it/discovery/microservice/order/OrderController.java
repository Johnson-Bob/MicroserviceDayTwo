package it.discovery.microservice.order;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.discovery.microservice.order.commands.CreateOrderCommand;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping
	public int createOrder(@RequestBody CreateOrderCommand request) {
		return orderService.createOrder(request.getBookId(), 
				request.getPrice(), request.getNumber(),
				request.getCustomerId()).getId();		
	}
	
	public void addBook(int orderId, int bookId, double price, int number) {
		orderService.addBook(orderId, bookId, price, number);
	}
	
	@PutMapping("{orderId}/complete")
	public void completeOrder(@PathVariable int orderId) {
		orderService.complete(orderId);
	}
	
	public void cancel(int orderId) {
		orderService.cancel(orderId);
	}
	
	public List<Order> findOrders() {
		return orderService.findOrders();
	}
}
