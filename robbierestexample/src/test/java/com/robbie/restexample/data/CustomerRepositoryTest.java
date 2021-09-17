package com.robbie.restexample.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.robbie.restexample.RestExampleApplication;

@SpringBootTest(classes = RestExampleApplication.class)
@Transactional
public class CustomerRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void createCustomeritSavesOK() {
		Customer created =  customerRepository.save(getCustomer());
		assertTrue(created!=null);
		assertTrue(created.getId()!=null);
	}
	
	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setForename("Testy");
		customer.setSurname("McTest");
		customer.setDateOfBirth(new Date());
		return customer;
	}

	@Test
	public void findForAccount89334625Test() {
		List<Customer> customers = customerRepository.findForAccount(89334625);
		assertTrue(customers.size()==1);
	}
	
	@Test
	public void findForAccount13657432Test() {
		List<Customer> customers = customerRepository.findForAccount(13657432);
		assertTrue(customers.size()==2);
	}
	
}
