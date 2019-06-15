package com.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.bank.entites.Account;
import com.bank.entites.Customer;
import com.bank.entites.Transaction;


public interface BankService {
	
	/**
	 * Return Account Balance By account ID
	 * @param accountId account identifier
	 * @return list of transactions of account
	 */
	BigDecimal AccountBalance(long accountId);
	

	/**
	 * Return list of all transactions By account ID
	 * @param accountId account identifier
	 * @return list of transactions of account
	 */
	List<Transaction> findAllTransactions(long accountId);
	
	
	
	 /**
     *
     * @param deposit account
     * @param amount the amount to deposit
     * @return Modified account state is returned, showing new balance.
     * @throws ServiceException Invalid transaction
     * amount or invalid account.
     */
   
	Account creditAccountMoney(long accountId, Long amount);
	
	 /**
    * deposit account & make sure there is no stoppages on the Account
    * I mean maybe this Account own the bank with money 
    * so we check there is no stoppages on the account
    * @param account Verfication by Account ID
    * @param amount the amount to deposit
    * @return return new balance.
    * @throws ServiceException Invalid transaction
    * amount or invalid account.
    */
  
	Account depitAccountMoney(long accountId, Long amount);
	
	 /**
     * Transfer consists of two process always
     * Debit first Account 
     * Credit second Account
     * @param Debit first Account 
     * @param Credit second Account
     * @param Ammount of Money to be transfered 
     * @return status of the transaction also new balance in Debit first Account.
     */
    Account transfer(long fromAccount, long toAccount, Long amount);
    
    /**
     * Last implementation is create customer Account 
     * & save it
     * 
     */
    Account createCustomerAccount(long customerId, Account account);
    
    
    /**
     * Last implementation is create customer 
     * & save it
     * 
     */
    
     Customer createCustomer(Customer customer);
      

}
