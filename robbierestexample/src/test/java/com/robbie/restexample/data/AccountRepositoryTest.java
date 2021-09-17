package com.robbie.restexample.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.robbie.restexample.RestExampleApplication;


@SpringBootTest(classes = RestExampleApplication.class)
@Transactional
public class AccountRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void createAccountItSavesOK() {
		Account created =  accountRepository.save(getAccount());
		assertTrue(created!=null);
		assertTrue(created.getId()!=null);
	}
	
	private Account getAccount() {
		Account account = new Account();
		account.setAccountNumber(77777777);
		return account;
	}

	@Test
	public void findForCustomer1Gets1Account() {
		List<Account> accs = findForCustomer(1l);
		assertTrue(accs.size()==1);
	}
	
	@Test
	public void findForCustomer4Gets2Accounts() {
		List<Account> accs = findForCustomer(4l);
		assertEquals(2, accs.size(),"Expected 2 accounts for customer 4, got "+accs.size());
	}
	
	@Test
	public void testAddCustomerAccount() {
		Optional<Customer> customerOpt =  customerRepository.findById(1l);
		assertTrue(customerOpt.isPresent());
		Customer customer = customerOpt.get();
		assertEquals(1, customer.getAccounts().size(),"Expected 1 accounts for customer 1, got "+customer.getAccounts().size());
		Account newAccount = getAccount();
		customer.getAccounts().add(newAccount);
		customerRepository.save(customer);
		
		// now get account to make sure has been saved, there should now be two accounts for the customer
		List<Account> accs = accountRepository.findForCustomer(1l);
		assertEquals(2, accs.size(),"Expected 2 accounts for customer 1, got "+accs.size());
		int count = 0;
		for (Account acc : accs) {
			assertTrue(acc.getId()!=null);
			if (count==0) {
				assertEquals(acc.getAccountNumber(),13657432);
			}
			else {
				assertEquals(acc.getAccountNumber(),77777777);
			}
			count++;
		}
	}
	
	private List<Account> findForCustomer(Long customerId) {
		return accountRepository.findForCustomer(customerId);
	}
}
