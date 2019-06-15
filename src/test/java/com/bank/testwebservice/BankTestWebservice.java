package com.bank.testwebservice;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bank.entites.Account;
import com.bank.entites.Customer;
import com.bank.service.BankService;


@SpringBootTest
@Transactional
public class BankTestWebservice {
	
	@Autowired
	BankService bankService;

	@Before
	public void setup() {

		Customer customer = new Customer();
		customer.setAddress("Egypt ,Cairo");
		customer.setEmail("bavly500@gmail.com");
		customer.setName("Bav");
		customer.setPhoneNumber("+201273660751");

		customer = bankService.createCustomer(customer);

		Account account = new Account();
		account.setName("Current Account");

		account = bankService.createCustomerAccount(customer.getId(), account);

		bankService.creditAccountMoney(account.getId(), (long) (1000));
	}

}
