package com.robbie.restexample.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query(value = "select a from Account a join a.customers c where c.id = ?1")
	List<Account> findForCustomer(Long customerId);

}
