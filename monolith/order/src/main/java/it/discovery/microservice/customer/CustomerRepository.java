package it.discovery.microservice.customer;

import java.util.List;

public interface CustomerRepository {

	Customer findById(int customerId);

	void save(Customer customer);

	List<Customer> findAll();

}
