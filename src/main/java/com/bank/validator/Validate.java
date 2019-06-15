package com.bank.validator;

public interface Validate {
	
	/**
	 * check the account Number exist or not
	 *
	 * @param accountNumber the string of numbers to be validated
	 * @return boolean true or false
	 */
	boolean accountIsNumber(String accountNumber);
	
	/**
	 * this method checks if account exist
	 *
	 * @param accountNumber the account number to be checked for existence
	 * @return a <code>boolean</code>
	 */
	boolean AccountExist(long accountNumber);
	
	
	/**
	 * 
	 *
	 * @param debitAccountNumber the account number to be checked for sufficient
	 *                           fund
	 * @param transactionAmount  the amount to be deducted from the account
	 * @return a <code>boolean</code>
	 */
	public boolean hasEnoughBalance(long debitAccountNumber, Long transactionAmount);
	
	

}
