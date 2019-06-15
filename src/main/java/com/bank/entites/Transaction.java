package com.bank.entites;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TRANSACTION_GEN")
	@Column(name="TransactionID")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "AccountID")
	@JsonBackReference
	private Account account;

	private typeCode code;
	private Date transactionDate;
	private long amount;
	private BigDecimal balance;
	private String currency;
	private TransferStatus transferStatus;
	
	
	

	public enum typeCode {
		DEBIT, CREDIT
	}

     public enum TransferStatus {
		SUCCESS, FAILED
	}

	public Transaction(Long id, Account account, typeCode code, Date transactionDate, long amount,
			BigDecimal balance, String currency, TransferStatus transferStatus) {
		super();
		this.id = id;
		this.account = account;
		this.code = code;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.balance = balance;
		this.currency = currency;
		this.transferStatus = transferStatus;
	}

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", account=" + account + ", code=" + code + ", transactionDate="
				+ transactionDate + ", amount=" + amount + ", balance=" + balance + ", currency=" + currency
				+ ", transferStatus=" + transferStatus + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the code
	 */
	public typeCode getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(typeCode code) {
		this.code = code;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the transferStatus
	 */
	public TransferStatus getTransferStatus() {
		return transferStatus;
	}

	/**
	 * @param transferStatus the transferStatus to set
	 */
	public void setTransferStatus(TransferStatus transferStatus) {
		this.transferStatus = transferStatus;
	}

	@Override
	public boolean equals(Object x) {
		if (this == x)
			return true;
		if (!(x instanceof Transaction))
			return false;

		Transaction that = (Transaction) x;

		if (!id.equals(that.id))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
