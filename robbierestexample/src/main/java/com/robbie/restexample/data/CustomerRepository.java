package com.robbie.restexample.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query(value = "select c from Customer c join c.accounts ac where ac.accountNumber = ?1")
	List<Customer> findForAccount(Integer accountNumber);

}
