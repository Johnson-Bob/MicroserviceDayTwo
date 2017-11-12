package it.discovery.microservice.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
	private int bookId;
	
	private int number;
	
	private int customerId;
	
	private double price;

}
