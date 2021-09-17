package com.robbie.restexample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robbie.restexample.data.Customer;
import com.robbie.restexample.data.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/getforaccount/{accountNumber}")
	public List<Customer> getForAccount(@PathVariable("accountNumber") Integer accountNumber) {
		List<Customer> customers = customerRepository.findForAccount(accountNumber);
		return customers;
	}
	
	
	
}