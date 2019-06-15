package com.bank.entites;

import java.math.BigDecimal;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "Account")

public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="ACCOUNT_GEN")
	@Column(name="AccountID")
	private Long id;

	private String name;
	private Date opened;
	private Date closed;
	private BigDecimal balance;
	private String Currency;
	
    @ManyToMany(mappedBy = "Accounts")
    private List<Customer> customer ;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
	@JsonManagedReference
	private List<Transaction> transaction;
	
	
	

	public Account(Long id, String name, Date opened, Date closed, BigDecimal balance, String currency,
			List<Customer> customer, List<Transaction> transaction) {
		super();
		this.id = id;
		this.name = name;
		this.opened = opened;
		this.closed = closed;
		this.balance = balance;
		Currency = currency;
		this.customer = customer;
		this.transaction = transaction;
	}




	public Account() {
		// TODO Auto-generated constructor stub
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @return the opened
	 */
	public Date getOpened() {
		return opened;
	}




	/**
	 * @param opened the opened to set
	 */
	public void setOpened(Date opened) {
		this.opened = opened;
	}




	/**
	 * @return the closed
	 */
	public Date getClosed() {
		return closed;
	}




	/**
	 * @param closed the closed to set
	 */
	public void setClosed(Date closed) {
		this.closed = closed;
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
		return Currency;
	}




	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}




	/**
	 * @return the customer
	 */
	public List<Customer> getCustomer() {
		return customer;
	}




	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}




	/**
	 * @return the transaction
	 */
	public List<Transaction> getTransaction() {
		return transaction;
	}




	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}




	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
