package com.robbie.restexample.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.robbie.restexample.data.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class CustomerControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getForAccount89334625ShouldReturnCustomer3() throws Exception {
		
		String urlPrefix = "http://localhost:" + port + "/customer/";
		String url = urlPrefix+"getforaccount/89334625";
		
		ResponseEntity<Customer[]> response = restTemplate.getForEntity(url,Customer[].class);
		Customer[] customers = response.getBody();
		assertTrue(customers!=null);
		assertTrue(customers.length==1);
		Customer customer = customers[0];
		assertEquals(customer.getId(), 3);
		
	}
    
	@Test
	public void getForAccount13657432ShouldReturnCustomers1and2() throws Exception {
		
		String urlPrefix = "http://localhost:" + port + "/customer/";
		String url = urlPrefix+"getforaccount/13657432";
		
		ResponseEntity<Customer[]> response = restTemplate.getForEntity(url,Customer[].class);
		Customer[] customers = response.getBody();
		assertTrue(customers!=null);
		assertTrue(customers.length==2);
		
		// 	can't guarantee order of customers returned
		boolean gotcust1 = false;
		boolean gotcust2 = false;
		Customer customer1 = customers[0];
		Customer customer2 = customers[1];
		long cust1id = customer1.getId();
		long cust2id = customer2.getId();
		if (cust1id==1l) {
			gotcust1 = true;
		}
		else if (cust1id==2l) {
			gotcust2 = true;
		}
		if (cust2id==1l) {
			gotcust1 = true;
		}
		else if (cust2id==2l) {
			gotcust2 = true;
		}
		assertTrue(gotcust1);
		assertTrue(gotcust2);
	}
	
	@Test
	public void getForAccount88889999ShouldReturnEmptyArray() throws Exception {
		
		String urlPrefix = "http://localhost:" + port + "/customer/";
		String url = urlPrefix+"getforaccount/88889999";
		
		ResponseEntity<Customer[]> response = restTemplate.getForEntity(url,Customer[].class);
		Customer[] customers = response.getBody();
		assertTrue(customers!=null);
		assertTrue(customers.length==0);
		
	}
}
