package com.robbie.restexample.web;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.robbie.restexample.data.Account;
import com.robbie.restexample.data.AccountRepository;
import com.robbie.restexample.data.Customer;
import com.robbie.restexample.data.CustomerRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
		
	@Test
	public void getForCustomer1ShouldReturnAccount1() throws Exception {
		
		String urlPrefix = "http://localhost:" + port + "/account/";
		String url = urlPrefix+"getforcustomer/1";
		
		ResponseEntity<Account[]> response = restTemplate.getForEntity(url,Account[].class);
		Account[] accounts = response.getBody();
		assertTrue(accounts!=null);
		assertTrue(accounts.length==1);
		Account account = accounts[0];
		assertEquals(account.getAccountNumber(), 13657432);
		
	}
    
	@Test
	public void getForCustomer4ShouldReturn2Accounts() throws Exception {
		
		String urlPrefix = "http://localhost:" + port + "/account/";
		String url = urlPrefix+"getforcustomer/4";
		
		ResponseEntity<Account[]> response = restTemplate.getForEntity(url,Account[].class);
		Account[] accounts = response.getBody();
		assertTrue(accounts!=null);
		assertTrue(accounts.length==2);
		Account account = accounts[0];
		assertEquals(account.getAccountNumber(), 87421521);
		Account account2 = accounts[1];
		assertEquals(account2.getAccountNumber(), 32421721);
	}
	
	@Test
	public void getForCustomer4ShouldReturnEmptyArray() throws Exception {
		
		String urlPrefix = "http://localhost:" + port + "/account/";
		String url = urlPrefix+"getforcustomer/5";
		
		ResponseEntity<Account[]> response = restTemplate.getForEntity(url,Account[].class);
		Account[] accounts = response.getBody();
		assertTrue(accounts!=null);
		assertTrue(accounts.length==0);
	}
	
	

	@Test
	public void addNewAccountForCustomer1ShouldSaveIt() throws Exception {
		String urlPrefix = "http://localhost:" + port + "/account/";
		String url = urlPrefix+"addForCustomer/1";
		Account account = new Account();
		account.setAccountNumber(77777777);
		restTemplate.postForObject(url,account,Account.class);
		
		Optional<Customer> customerOpt = customerRepository.findById(1l);
		Customer customer = customerOpt.get();
		List<Account> cust1accounts = customer.getAccounts();
		assertTrue(cust1accounts!=null);
		assertTrue(cust1accounts.size()==2);
		Account account1 = cust1accounts.get(0);
		assertEquals(account1.getAccountNumber(), 13657432);
		Account account2 = cust1accounts.get(1);
		assertEquals(account2.getAccountNumber(), 77777777);
		
		// clean up
		customer.getAccounts().remove(account2);
		customerRepository.save(customer);
		account2.getCustomers().remove(customer);
		accountRepository.save(account2);
	}
	
	
}
