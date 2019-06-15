package com.bank.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.entites.Account;
import com.bank.entites.Customer;
import com.bank.entites.Transaction;
import com.bank.entites.Transaction.TransferStatus;
import com.bank.entites.Transaction.typeCode;
import com.bank.repositories.AccountRepository;
import com.bank.repositories.CustomerRepository;
import com.bank.repositories.TransactionRepository;
import com.bank.validator.Validate;

@Service
@Transactional

public class BankServiceImpl implements BankService {

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	private final Validate validate;

	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	public BankServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository,
			TransactionRepository transactionRepository, Validate validate) {
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.validate = validate;

	}

	/**
	 *
	 * @param deposit account
	 * @param amount  the amount to deposit
	 * @return Modified account state is returned, showing new balance.
	 * @throws ServiceException Invalid transaction amount or invalid account.
	 */
	@Override
	public Account creditAccountMoney(long accountId, Long amount) {

		Account finalAccount = null;

		try {

			// Credit to account

			Iterable<Account> result = accountRepository.findByAccountId(accountId);

			finalAccount = (Account) result;

			//
			if (validate.accountIsNumber(finalAccount.getId().toString())) {

				// check account exist
				if (validate.AccountExist(finalAccount.getId())) {

					// check debit account balance is have money
					if (validate.hasEnoughBalance(finalAccount.getId(), amount)) {

						// Withdraw
						//
						// finalAccount.getBalance()

						BigDecimal newBalance = finalAccount.getBalance().add(BigDecimal.valueOf(amount));

						finalAccount.setBalance(newBalance);

						finalAccount = accountRepository.save(finalAccount);

						Transaction transactionHistory = new Transaction();
						transactionHistory.setAccount(finalAccount);
						transactionHistory.setAmount(amount);
						transactionHistory.setBalance(newBalance);
						transactionHistory.setCode(typeCode.DEBIT);
						transactionHistory.setTransactionDate(new Date());
						transactionHistory.setTransferStatus(TransferStatus.SUCCESS);
						transactionHistory.setCurrency("Euro");

						// Record withdrawal transaction
						transactionRepository.save(transactionHistory);

						logger.info("DepitAccount amount {} from account {}", amount.toString(), accountId);

						return finalAccount;
					}
				}
			}
		} catch (Exception e) {
			logger.error("depitAccountMoney Method : error: {}", e.getMessage());
		}
		return finalAccount;

	}

	/**
	 * deposit account & make sure there is no stoppages on the Account I mean maybe
	 * this Account own the bank with money so we check there is no stoppages on the
	 * account
	 * 
	 * @param account Verfication by Account ID
	 * @param amount  the amount to deposit
	 * @return return new balance.
	 * @throws ServiceException Invalid transaction amount or invalid account.
	 */
	@Override
	public Account depitAccountMoney(long accountId, Long amount) {

		Account finalAccount = null;

		try {

			if (amount == null)
				throw new ExceptionServiceImpl("Invalid amount");

			if (amount <= 0)
				throw new ExceptionServiceImpl(" Negative ammount number");

			// Depoit to account

			Iterable<Account> result = accountRepository.findByAccountId(accountId);

			finalAccount = (Account) result;

			//
			if (validate.accountIsNumber(finalAccount.getId().toString())) {

				// check account exist
				if (validate.AccountExist(finalAccount.getId())) {

					// check debit account balance is have money
					if (validate.hasEnoughBalance(finalAccount.getId(), amount)) {

						// Withdraw
						//
						// finalAccount.getBalance()

						BigDecimal newBalance = finalAccount.getBalance().subtract(BigDecimal.valueOf(amount));

						finalAccount.setBalance(newBalance);

						finalAccount = accountRepository.save(finalAccount);

						Transaction transactionHistory = new Transaction();
						transactionHistory.setAccount(finalAccount);
						transactionHistory.setAmount(amount);
						transactionHistory.setBalance(newBalance);
						transactionHistory.setCode(typeCode.DEBIT);
						transactionHistory.setTransactionDate(new Date());
						transactionHistory.setTransferStatus(TransferStatus.SUCCESS);
						transactionHistory.setCurrency("Euro");

						// Record withdrawal transaction
						transactionRepository.save(transactionHistory);

						logger.info("DepitAccount amount {} from account {}", amount.toString(), accountId);

						return finalAccount;
					}
				}
			}
		} catch (Exception e) {
			logger.error("depitAccountMoney Method : error: {}", e.getMessage());
		}
		return finalAccount;

	}

	/**
	 * this method will check the successful transaction or decline a transfer
	 * request
	 *
	 * @param request the transfer request payload containing all transaction detail
	 * @return a <code>TransactionResponse</code> object stating the status of the
	 *         transfer request
	 */
	@Override
	public Account transfer(long fromAccount, long toAccount, Long amount) {

		Account result = null;
		try {

			

			if (fromAccount == toAccount)
				throw new ExceptionServiceImpl("Same Account Transfer Not applicable");
			// Depit Account Money
			result = depitAccountMoney(fromAccount, amount);

			// Credit Account Money
			creditAccountMoney(toAccount, amount);

			logger.info("Transferred amount {} from account {} to account {}", amount, fromAccount, toAccount);

			return result;
		} catch (Exception e) {
			logger.error("accountNumber does not Exist : error: { ", e.getMessage(), "}");

		}
		return result;

	}

	/**
	 * Return Account Balance By account ID
	 * 
	 * @param accountId account identifier
	 * @return list of transactions of account
	 */

	@Override
	public BigDecimal AccountBalance(long accountId) {

		Iterable<Account> result = accountRepository.findByAccountId(accountId);
		Account finalAccount = (Account) result;
		BigDecimal finalBalance = null;

		logger.info("customerBalance: account {}", finalAccount);

		// validate accountNumber
		if (validate.accountIsNumber(finalAccount.getId().toString())) {
			// check account exist
			if (validate.AccountExist(finalAccount.getId()) && finalAccount.getBalance() != null
					&& finalAccount.getBalance().compareTo(BigDecimal.ZERO) == 1)
				finalBalance = finalAccount.getBalance();

		}

		return finalBalance;
	}

	/**
	 * Return list of all transactions By account ID
	 * 
	 * @param accountId account identifier
	 * @return list of transactions of account
	 */
	@Override
	public List<Transaction> findAllTransactions(long accountId) {

		Iterable<Account> result = accountRepository.findByAccountId(accountId);

		List<Transaction> results = new ArrayList<>();
		Account finalAccount = (Account) result;

		logger.info("findAllTransactions: account {}", finalAccount);

		// validate accountNumber
		if (validate.accountIsNumber(finalAccount.getId().toString())) {
			// check account exist
			if (validate.AccountExist(finalAccount.getId()))
				transactionRepository.findByAccountId(accountId).forEach(results::add);
		}

		return results;
		// TODO Auto-generated method stub
	}

	/**
	 * Last implementation is create customer & save it
	 * 
	 * @param Customer Id
	 * @param Account
	 */
	@Override
	public Account createCustomerAccount(long customerId, Account account) {

		Iterable<Customer> customerFinal = customerRepository.findByCustomerId(customerId);

		Customer finalCustomer = (Customer) customerFinal;

		logger.info("finalCustomer: Customer {}", finalCustomer);

		List<Customer> customers = new ArrayList<>();

		customers.add(finalCustomer);

		account.setBalance(BigDecimal.ZERO);
		account.setOpened(new Date());
		account.setClosed(null);
		account.setCustomer(customers);

		logger.info("createCustomerAccount: Account {}", account);

		Account result = accountRepository.save(account);

		logger.info("Created account for customer {}, account: {} ", finalCustomer.getId(), account.getId());

		return result;
	}
	
	/**
	 * Add Customer
	 */
	  @Override
	    public Customer createCustomer(Customer customer) {
	       return customerRepository.save(customer);
	    }

}
