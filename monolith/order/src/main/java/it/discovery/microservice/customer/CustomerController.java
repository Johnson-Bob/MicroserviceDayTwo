package it.discovery.microservice.customer;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private CustomerRepository customerRepository;

	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);				
	}

	public List<Customer> findCustomers() {
		return customerRepository.findAll();				
	}

}
