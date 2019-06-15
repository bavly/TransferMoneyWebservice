package com.bank.requestbody;

public class TransferRequest {
	
	Long accountFrom;
	
	Long accountTo;
	
	long amount;
	
	

	@Override
	public String toString() {
		return "TransferRequest [accountFrom=" + accountFrom + ", accountTo=" + accountTo + ", amount=" + amount + "]";
	}

	public TransferRequest(Long accountFrom, Long accountTo, long amount) {
		super();
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}

	/**
	 * @return the accountFrom
	 */
	public Long getAccountFrom() {
		return accountFrom;
	}

	/**
	 * @param accountFrom the accountFrom to set
	 */
	public void setAccountFrom(Long accountFrom) {
		this.accountFrom = accountFrom;
	}

	/**
	 * @return the accountTo
	 */
	public Long getAccountTo() {
		return accountTo;
	}

	/**
	 * @param accountTo the accountTo to set
	 */
	public void setAccountTo(Long accountTo) {
		this.accountTo = accountTo;
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


}
