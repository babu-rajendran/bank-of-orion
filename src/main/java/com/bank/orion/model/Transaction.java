package com.bank.orion.model;

public class Transaction implements Item {

	private String amount;
	private String transactionDate;
	private String description;
	private String receivingAccountNumber;
	private String sendingAccountNumber;
	private String transactionStatus;
	private String transactionID;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceivingAccountNumber() {
		return receivingAccountNumber;
	}

	public void setReceivingAccountNumber(String receivingAccountNumber) {
		this.receivingAccountNumber = receivingAccountNumber;
	}

	public String getSendingAccountNumber() {
		return sendingAccountNumber;
	}

	public void setSendingAccountNumber(String sendingAccountNumber) {
		this.sendingAccountNumber = sendingAccountNumber;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", transactionDate=" + transactionDate + ", description=" + description
				+ ", receivingAccountNumber=" + receivingAccountNumber + ", sendingAccountNumber="
				+ sendingAccountNumber + ", transactionStatus=" + transactionStatus + ", transactionID=" + transactionID
				+ "]";
	}

}
