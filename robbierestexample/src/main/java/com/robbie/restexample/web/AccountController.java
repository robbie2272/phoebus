package com.robbie.restexample.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robbie.restexample.data.Account;
import com.robbie.restexample.data.AccountRepository;
import com.robbie.restexample.data.Customer;
import com.robbie.restexample.data.CustomerRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/getforcustomer/{customerId}")
	public List<Account> getByAccount(@PathVariable("customerId") Long customerId) {
		List<Account> accounts = accountRepository.findForCustomer(customerId);
		return accounts;
	}
	
	@PostMapping("/addForCustomer/{customerId}")
	public void addAccount(@RequestBody Account newAccount, @PathVariable("customerId") Long customerId) {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			customer.getAccounts().add(newAccount);
			customerRepository.save(customer);
		}
	}
}
