package it.discovery.microservice.order.commands;

import lombok.Data;

@Data
public class CreateOrderCommand {
	private int bookId;
	
	private int number;
	
	private int customerId;
	
	private double price;
}
