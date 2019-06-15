package com.bank.validator;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bank.entites.Account;
import com.bank.repositories.AccountRepository;

public class ValidateImpl implements Validate {

	private static final Logger logger = LoggerFactory.getLogger(ValidateImpl.class);

	@Autowired
	private  AccountRepository accountRepository;
	
	


	/**
	 * check the account Number exist or not
	 *
	 * @param accountNumber the string of numbers to be validated
	 * @return boolean true or false
	 */
	@Override
	public boolean accountIsNumber(String accountNumber) {
		try {
			if (accountNumber == null || accountNumber.length() == 0) {
				return false;
			}
			return accountNumber.trim().chars().allMatch(Character::isDigit);

		} catch (Exception e) {
			logger.error("accountNumber does not Exist : error: {}", e.getMessage());
		}
		return accountNumber.trim().chars().allMatch(Character::isDigit);
	}

	/**
	 * this method checks if account exist
	 *
	 * @param accountNumber the account number to be checked for existence
	 * @return a <code>boolean</code>
	 */
	@Override
	public boolean AccountExist(long accountNumber) {
		Account account = (Account) accountRepository.findByAccountId(accountNumber);
		try {
			logger.info("Account Exist {}", account);
			return (account.getId() == 0 || account == null) ? false : true;
		} catch (Exception e) {
			logger.error("accountNumber does not Exist : error: { ", e.getMessage(), "}");
		}
		return (account.getId() == 0 || account == null) ? false : true;
	}

	/**
	 * 
	 *
	 * @param debitAccountNumber the account number to be checked for sufficient
	 *                           fund
	 * @param transactionAmount  the amount to be deducted from the account
	 * @return a <code>boolean</code>
	 */
	@Override
	public boolean hasEnoughBalance(long debitAccountNumber, Long transactionAmount) {
		try {
			Account account = (Account) accountRepository.findByAccountId(debitAccountNumber);
			System.out.println("Account Exist : " + account);
			return ( account.getBalance().compareTo(BigDecimal.valueOf(transactionAmount)) ==  1 ) ? true : false;
		} catch (Exception e) {
			logger.error("hasEnoughBalance does not balanced Customer : error: { ", e.getMessage(), "}");
		}
		return false;

	}

}
