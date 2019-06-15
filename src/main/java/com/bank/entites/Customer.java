package com.bank.entites;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="CUSTOMER_GEN")
	@Column(name="CustomerID")
	private Long id;

	private String name;
	private String email;

	private String address;
	private String phoneNumber;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "CustomerAccounts", joinColumns = { @JoinColumn(name = "CustomerID") }, inverseJoinColumns = {
			@JoinColumn(name = "AccountID") })
	List<Account> accounts;

	public Customer(Long id, String name, String email, String address, String phoneNumber, List<Account> accounts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.accounts = accounts;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", accounts=" + accounts + "]";
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
