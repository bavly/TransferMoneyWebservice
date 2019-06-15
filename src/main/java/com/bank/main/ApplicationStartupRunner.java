package com.bank.main;


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.bank.entites.Account;
import com.bank.entites.Customer;
import com.bank.service.BankService;



public class ApplicationStartupRunner implements CommandLineRunner  {
	
	
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
	BankService bankService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application Started !!");
        
        /**
         * add some data in Database
         */
        
        
        Customer customer = new Customer();
        customer.setAddress("Egypt ,Cairo");
        customer.setEmail("bavly500@gmail.com");
        customer.setName("Bav");
        customer.setPhoneNumber("+201273660751");
        
        customer = bankService.createCustomer(customer);
        
        Account account = new Account();
        account.setName("Current Account");
        
        account = bankService.createCustomerAccount(customer.getId(), account);

        bankService.creditAccountMoney(account.getId(),(long) (1000));





    }

}
