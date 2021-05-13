package com.bank.orion.dto;

public class AdminForm {
	private String searchUser;
	private String searchTransaction;
	private String email;
	private String transactionId;

	public String getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}

	public String getSearchTransaction() {
		return searchTransaction;
	}

	public void setSearchTransaction(String searchTransaction) {
		this.searchTransaction = searchTransaction;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
